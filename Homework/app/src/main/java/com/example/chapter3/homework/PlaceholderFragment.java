package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        String[] item = {"user1","user1","user1","user1","user1","user1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,item);
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        ListView listView = view.findViewById(R.id.my_list);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                getView().findViewById(R.id.my_list).setAlpha(0);
                getView().findViewById(R.id.my_list).setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(getView().findViewById(R.id.my_list),"alpha",1);
                objectAnimator1.setDuration(1000);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(getView().findViewById(R.id.animation_view_1),"alpha",0);
                objectAnimator2.setDuration(1000);
                objectAnimator1.start();
                objectAnimator2.start();

            }
        }, 5000);
    }
}
