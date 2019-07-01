package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * Created by Administrator on 2018/9/6.
 */

public class MyAdapter extends BaseAdapter {
    private List<String> listTitle;
    private List<String> listRemark;
    private List<String> listTime;
    private PullParser pullParser;

    private Context context;
    public MyAdapter(List<String> listTitle,List<String> listRemark,List<String> listTime,Context context){
        this.listTitle = listTitle;
        this.listRemark = listRemark;
        this.listTime = listTime;
        this.context=context;
    }
    @Override
    public int getCount() {
        return listRemark.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            //通过一个打气筒 inflate 可以把一个布局转换成一个view对象
            view=View.inflate(context,R.layout.im_list_item,null);
        }else {
            view=convertView;//复用历史缓存对象
        }

        ImageView img=(ImageView) view.findViewById(R.id.robot_notice);
        TextView tvTitle=(TextView)view.findViewById(R.id.tv_title);
        TextView tvRemark=(TextView)view.findViewById(R.id.tv_description);
        TextView tvTime = (TextView)view.findViewById(R.id.tv_time);
        tvTitle.setText(listTitle.get(position));
        tvRemark.setText(listRemark.get(position));
        tvTime.setText(listTime.get(position));
        return view;
    }

}
