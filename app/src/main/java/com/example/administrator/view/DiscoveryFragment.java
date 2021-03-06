package com.example.administrator.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.example.administrator.R;
import com.example.administrator.adapter.fenxiangkuangAdapter;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.Strategy;
import com.example.administrator.util.AMapUtil;
import com.example.administrator.util.FileUtils;
import com.example.administrator.model.fenxiangkuang;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: wyz:从服务器获取信息并存储
 */

/**
 * A simple {@link Fragment} subclass.
 */
//陈子恒，发现碎片界面，我弄了每个item的跳转和下拉刷新的模板，主逻辑由宜钊完成。
public class DiscoveryFragment extends Fragment  {

    private SwipeRefreshLayout swipeRefresh;
    private View vkuanti;
    private List<fenxiangkuang> fenxiangkuangList=new ArrayList<>();
    private List<Strategy> strategies;
    private AMapLocation curLocation;
    DateFormat format = new SimpleDateFormat("yyyy MM-dd HH:mm:ss");
    private static int start = 1;
    private static int end = 10;
    private List<fenxiangkuang>newDatas=new ArrayList<fenxiangkuang>();
    private static String TAG = "DiscoveryFragment";

    public DiscoveryFragment() {

        start=1;
        end= 10;
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery,container,false);
        vkuanti=inflater.inflate(R.layout.sharing_templet,container,false);
//        inittest1();
        final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.fenxiangliebiao);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final fenxiangkuangAdapter adapter=new fenxiangkuangAdapter(fenxiangkuangList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new fenxiangkuangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                TextView textView = view.findViewById(R.id.strategy_time);
                TextView address = view.findViewById(R.id.address);
                String s = address.getText().toString()+textView.getText().toString();

                Log.i(TAG, "onItemClick: "+s);

                Intent intent = new Intent(getActivity(),StrategyActivity.class);
                intent.putExtra("otherStrategies",s);



                position=recyclerView.getChildAdapterPosition(view);
                startActivity(intent);
            }
        });
        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh1);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                DiscoveryFragment.ConnectThread sendMessage = new DiscoveryFragment.ConnectThread();

                sendMessage.start();
                try {
                    sendMessage.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMessage.interrupt();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        fenxiangkuangList.clear();

                        if(strategies.size()!=0){
                            for(Strategy strategy:strategies){

                                fenxiangkuang item=new fenxiangkuang("www",strategy.getTitle(),format.format(strategy.getPublish_time()),"0","0",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
                                newDatas.add(0,item);
                            }
                            adapter.addItem(newDatas);
                        }
                            swipeRefresh.setRefreshing(false);




//                        fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","156","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
//                        newDatas.add(0,b);
//                        fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","170","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
//                        newDatas.add(0,c);
//                        fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","104","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
//                        newDatas.add(0,a);

                    }
                },2000);
            }
        });

        return view;
    }

    private void inittest1(){
            fenxiangkuang a=new fenxiangkuang("旅法师","福州大学","2018.11.27","10","26",R.drawable.fuzhoudaxue,R.drawable.touxianglvfa,R.drawable.dianzan1,R.drawable.pinglun,R.drawable.shoucan);
            fenxiangkuangList.add(a);
            ImageView imageView = (ImageView)vkuanti.findViewById(R.id.findtupian);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),StrategyActivity.class);
                    startActivity(intent);
                }
            });
            fenxiangkuang b=new fenxiangkuang("利威尔兵短","闽江学院","2018.11.25","15","32",R.drawable.malatang,R.drawable.touxiangbinzhang,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
            fenxiangkuangList.add(b);
            fenxiangkuang c=new fenxiangkuang("血大板","师大学生街","2018.11.21","17","46",R.drawable.naicha,R.drawable.longnvpu,R.drawable.dianzan2,R.drawable.pinglun,R.drawable.shoucang);
            fenxiangkuangList.add(c);
    }


    private class ConnectThread extends Thread{


        /**
         * 从服务器获取10条总攻略 并且进行存储
         */
        @Override
        public void run() {
            super.run();
            ConnTool connTool = new ConnTool();
            curLocation = AMapUtil.getCurLocation();
            strategies = connTool.discover(curLocation.getLongitude(),curLocation.getLatitude(),start,end);
            if(strategies.size()==0){
//                ToastUtil.show(getContext(),"没有更多的内容了！");
            }
            else{

                for(Strategy strategy:strategies){

                    Gson gson = new Gson();


//                    for(DotStrategy dotStrategy:strategy.getDotStrategy()){
//                        try {
//                            dotStrategy.getPicture().setBitmapBytes(connTool.downloadImage(dotStrategy.getPicture().getUrl()));
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    }


                    try {
                        FileUtils.saveFile(gson.toJson(strategy),"otherStrategies",strategy.getTitle()+format.format(strategy.getPublish_time())+".txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }



//                ToastUtil.show(getContext(),"更新成功！");

            }

            start+=10;
            end+=10;




        }
    }


}
