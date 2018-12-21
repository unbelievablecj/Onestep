package com.example.administrator.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.MyWish;
import com.example.administrator.model.Strategy;
import com.example.administrator.util.FileSaveUtils;
import com.example.administrator.util.PictureUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DotStrategyActivity extends AppCompatActivity {

    private Button addToMyWishList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_strategy);
        String string =(String)getIntent().getSerializableExtra("dotStrategyDetail");
        String s = FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"dotStrategy/"+string+".txt");

        final DotStrategy dotStrategy = gson.fromJson(s,DotStrategy.class);

        TextView time = findViewById(R.id.doStrategy_time);
        DateFormat format = new SimpleDateFormat("yyyy MM-dd HH:mm:ss");
        time.setText(format.format(dotStrategy.getPublish_time()));

        TextView text = findViewById(R.id.doStrategy_text);
        text.setText(dotStrategy.getComment());

        ImageView img = findViewById(R.id.doStrategy_img);
        img.setImageBitmap(PictureUtil.getBitmap(dotStrategy.getPicture().getBitmapBytes()));

        TextView place = findViewById(R.id.doStrategy_place);
        place.setText(dotStrategy.getPlace_name());

        addToMyWishList = (Button)findViewById(R.id.add_to_my_wishlist);
        addToMyWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyWish myWish=new MyWish("0",dotStrategy.getPlace_name());
                Gson gson = new Gson();
                String temp = gson.toJson(myWish);
                Log.d("心愿单",temp);
                try {
                    FileSaveUtils.saveFileAdd(temp+",","SaveUser","myWishList.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }



}
