package com.example.administrator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.adapter.MyWishAdapter;
import com.example.administrator.model.MyWish;
import com.example.administrator.util.FileSaveUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MyWishListActivity extends AppCompatActivity {
    private List<MyWish> myWishList = new ArrayList<>();
    private JSONArray myWishArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);

        initMyWish();
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.my_wish_list);
        TextView textView = (TextView )findViewById(R.id.title_name);
        textView.setText("我的心愿单");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyWishAdapter adapter = new MyWishAdapter(myWishList);
        recyclerView.setAdapter(adapter);

    }


    private void initMyWish() {
        Gson gson = new Gson();
        String temp=FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"/SaveUser/myWishList.txt");
        temp.replace(" ","");
        temp = temp.substring(0,temp.length()-1);
//        myWishArray = gson.fromJson(temp,new TypeToken<List<MyWish>>(){}.getType());
//        MyWish w1 = new MyWish("1","福州大学");
//        myWishArray.put(gson.toJson(w1));
//        myWishList.add(w1);
//        MyWish w2 = new MyWish("2","丽江");
//        myWishArray.put(gson.toJson(w2));
//        myWishList.add(w2);
        Log.d("个人界面",temp);
        int i = 0;
//        for(i=0;i<myWishArray.length();i++)
//        {
//            FileSaveUtils.saveFileAdd(myWishArray.getJSONObject(i)
//        }
    }


}