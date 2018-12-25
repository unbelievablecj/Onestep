package com.example.administrator.view;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.R;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.model.fenxiangkuang;

import java.util.ArrayList;
import java.util.List;
//陈子恒，我的收藏界面
public class MycollectionActivity extends AppCompatActivity {

    private List<fenxiangkuang> MycollectionList = new ArrayList<>();
    private fenxiangkuangAdapter adapter;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Mycollectionliebiao);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final fenxiangkuangAdapter adapter = new fenxiangkuangAdapter(MycollectionList);
        recyclerView.setAdapter(adapter);
        initMycollection();//与后端的通信没弄好，暂时先这样初始化界面
    }

    private void initMycollection(){
        fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","170","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
        MycollectionList.add(c);
        fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","156","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
        MycollectionList.add(b);
        fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","104","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
        MycollectionList.add(a);

    }

}
