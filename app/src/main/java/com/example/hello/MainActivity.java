package com.example.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        final TextView tv1 = findViewById(R.id.tv1);
        final ImageView img = findViewById(R.id.img1);
        final EditText et = findViewById(R.id.et1);
        final Switch swt = findViewById(R.id.switch1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state){
                    tv1.setText("喜欢唱，跳");
                    img.setImageDrawable(getResources().getDrawable(R.drawable.xiaoc));
                    if(et.getText().toString().equals("鸡你太美")){
                        Log.d("按钮","密码正确");
                    }
                    else{
                        Log.d("按钮","密码错误");
                    }
                    Log.d("按钮","点击成功");
                    state = true;
                }
                else{
                    tv1.setText("rap，篮球");
                    img.setImageDrawable(getResources().getDrawable(R.drawable.test));
                    Log.d("按钮","点击成功");
                    state = false;
                }
            }
        });
    }


}
