package com.example.administrator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.adapter.MyWishAdapter;
import com.example.administrator.model.MyWish;
import com.example.administrator.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//陈玮   我的心愿单界面
public class MyWishListActivity extends AppCompatActivity {
    private List<MyWish> myWishList = new ArrayList<>();
    private Button back;
//    private JSONArray myWishArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);


        initMyWish();//初始化我的心愿单


        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.my_wish_list);
        TextView textView = (TextView )findViewById(R.id.title_name);
        textView.setText("我的心愿单");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final MyWishAdapter adapter = new MyWishAdapter(myWishList); //创建心愿单的recycleView(滚动菜单）




        back = (Button)findViewById(R.id.titleButton1) ;//用户点击返回或者系统返回按键之后，把修改后的心愿单存入本地文件中
        back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Gson gson = new Gson();
                        String t = gson.toJson(myWishList);
                        Log.d("我的心愿单",t);
                        FileUtils.saveFile(t,"SaveUser","myWishList.txt");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });



        recyclerView.setAdapter(adapter);

    }


    private void initMyWish() {
        Gson gson = new Gson();
        String temp=FileUtils.readFile(FileUtils.getRealPath()+"/SaveUser/myWishList.txt");
        if(temp.length()!=0) {
            myWishList = gson.fromJson(temp, new TypeToken<List<MyWish>>() {
            }.getType());//从文件读入心愿单并显示
        }

    }


    //监听系统返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //点击返回键
        if(keyCode==KeyEvent.KEYCODE_BACK){
            try {
                Gson gson = new Gson();
                String t = gson.toJson(myWishList);
                FileUtils.saveFile(t,"SaveUser","myWishList.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();

        }
        return super.onKeyDown(keyCode,event);
    }


}