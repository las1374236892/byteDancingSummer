package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String remark = intent.getStringExtra("remark");

        TextView Title = findViewById(R.id.tv_with_name);
        TextView Remark = findViewById(R.id.tv_content_info);

        Title.setText(title);
        Remark.setText(remark);
    }
}
