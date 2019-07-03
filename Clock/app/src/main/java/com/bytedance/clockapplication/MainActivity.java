package com.bytedance.clockapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bytedance.clockapplication.widget.Clock;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private View mRootView;
    private Clock mClockView;
    private final Handler myHandler = new handler(this);



    protected static class handler extends Handler{
        private final WeakReference<MainActivity> mActivityReference;

        public handler(MainActivity activity) {
            mActivityReference= new WeakReference<MainActivity>(activity);
        }//添加弱引用

        @Override
        public void handleMessage(Message msg){
            final MainActivity activity = mActivityReference.get();
            if (activity != null){
                activity.mClockView.setShowAnalog(activity.mClockView.isShowAnalog());
            }
        }
    }
    //通过弱引用解决内存泄露问题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root);
        mClockView = findViewById(R.id.clock);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i < 1000;i++) {
                    try {
                        Thread.sleep(1000);
                        mClockView.setShowAnalog(mClockView.isShowAnalog());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
        //不用handler，直接采用线程，可以避免内存泄露

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread.sleep(1000);
                while (true) {
                    Message msg = new Message();
                    myHandler.sendMessageDelayed(msg,1000);
                }
            }
        }).start();


        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClockView.setShowAnalog(!mClockView.isShowAnalog());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }

}
