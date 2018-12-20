package com.example.administrator.view;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.R;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.util.fenxiangkuang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment  {

    private SwipeRefreshLayout swipeRefresh;
    private View vkuanti;
    private List<fenxiangkuang> fenxiangkuangList=new ArrayList<>();
    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery,container,false);
        vkuanti=inflater.inflate(R.layout.sharing_templet,container,false);
        inittest1();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.fenxiangliebiao);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final fenxiangkuangAdapter adapter=new fenxiangkuangAdapter(fenxiangkuangList);
        recyclerView.setAdapter(adapter);
        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh1);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fenxiangkuangList.clear();
                        List<fenxiangkuang>newDatas=new ArrayList<fenxiangkuang>();
                        fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","156","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
                        newDatas.add(0,b);
                        fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","170","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
                        newDatas.add(0,c);
                        fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","104","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
                        newDatas.add(0,a);
                        adapter.addItem(newDatas);
                        swipeRefresh.setRefreshing(false);
                    }
                },2000);
            }
        });

        return view;
    }

    private void inittest1(){
            fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","104","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
            fenxiangkuangList.add(a);
            ImageView imageView = (ImageView)vkuanti.findViewById(R.id.findtupian);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),StrategyActivity.class);
                    startActivity(intent);
                }
            });
            fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","156","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
            fenxiangkuangList.add(b);
            fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","170","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
            fenxiangkuangList.add(c);
    }

}
