package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 大作业:实现一个抖音消息页面,所需资源已放在res下面
 */
public class Exercises3 extends AppCompatActivity {

    private ListView listView;
    //模拟新闻的标题数据
    private List<String> listTitle;
    //模拟新闻的信息数据
    private List<String> listRemark;
    private List<String> listTime;
    //创建适配器对象
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        initUI();
        initDate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(Exercises3.this,ChatRoom.class);
                intent.putExtra("title",listTitle.get(position));
                intent.putExtra("remark",listRemark.get(position));
                startActivity(intent);
            }
        });
    }
    public void initUI(){
        listView=(ListView) findViewById(R.id.listview);
        listTitle = new ArrayList<String>();
        listRemark = new ArrayList<String>();
        listTime = new ArrayList<String>();

    }
    public  void initDate(){
        //模拟创建数据
        for (int i=0;i<99;i++){
            listTitle.add("抖音小助手"+i);
            listRemark.add("第"+i+"号机器人给你跪下了砰砰砰");
            listTime.add(i+"分钟前");
        }
        adapter=new MyAdapter(listTitle,listRemark,listTime,this);
        listView.setAdapter(adapter);
    }

    /*private String data[] = {"抖音小助手","bb","cc","dd","aa","bb","cc","dd","aa","bb","cc","dd","aa","bb","cc","dd"};//假数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ListView listView = (ListView) findViewById(R.id.listview);//在视图中找到ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);//新建并配置ArrayAapeter
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(Exercises3.this,"点击了第：" + position, Toast.LENGTH_LONG).show();
            }
        });*/

}
