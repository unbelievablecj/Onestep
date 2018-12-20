package com.example.administrator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.adapter.MyWishAdapter;
import com.example.administrator.model.MyWish;

import java.util.ArrayList;
import java.util.List;

public class MyWishListActivity extends AppCompatActivity {
    private List<MyWish> myWishList = new ArrayList<>();

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
        MyWish w1 = new MyWish("1","福州大学",R.drawable.locationicon,R.drawable.deleteicon);
        myWishList.add(w1);
        MyWish w2 = new MyWish("2","丽江",R.drawable.locationicon,R.drawable.deleteicon);
        myWishList.add(w2);
    }


}