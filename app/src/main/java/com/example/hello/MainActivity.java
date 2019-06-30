package com.example.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state){
                    tv1.setText("喜欢唱，跳");
                    img.setImageDrawable(getResources().getDrawable(R.drawable.xiaoc));
                    state = true;
                }
                else{
                    tv1.setText("rap，篮球");
                    img.setImageDrawable(getResources().getDrawable(R.drawable.test));
                    state = false;
                }
            }
        });
    }


}
