package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    String msg = "Android : ";
    int viewCount = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.root1);
        Toast.makeText(this,"view的个数为"+getViewCount(v),Toast.LENGTH_SHORT).show();

    }

    public int getViewCount(View view) {
        if(view == null)
        {
            return 0;
        }

        if(view instanceof ViewGroup){
            viewCount++;
            for(int i=0; i < ((ViewGroup) view).getChildCount() ;i++)
            {
                View v = ((ViewGroup) view).getChildAt(i);
                if (v instanceof ViewGroup) {
                    viewCount += getViewCount(v);
                } else {
                    viewCount++;
                }
            }
            //Log.d(msg,"当前的view数为"+viewCount);
        }
        //todo 补全你的代码
        return viewCount;
    }
}
