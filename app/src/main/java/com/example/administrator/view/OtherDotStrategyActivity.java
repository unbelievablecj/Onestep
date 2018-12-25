package com.example.administrator.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.MyWish;
import com.example.administrator.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: 其他人的地点攻略页面
 */

public class OtherDotStrategyActivity extends AppCompatActivity {

    private Button addToMyWishList;
    private int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_dot_strategy);
        String string =(String)getIntent().getSerializableExtra("dotStrategyDetail");
        String s = FileUtils.readFile(FileUtils.getRealPath()+"otherDotStrategy/"+string+".txt");

        final DotStrategy dotStrategy = gson.fromJson(s,DotStrategy.class);

        TextView time = findViewById(R.id.other_doStrategy_time);
        DateFormat format = new SimpleDateFormat("yyyy MM-dd HH:mm:ss");
        time.setText(format.format(dotStrategy.getPublish_time()));

        TextView text = findViewById(R.id.other_doStrategy_text);
        text.setText(dotStrategy.getComment());

//        ImageView img = findViewById(R.id.other_doStrategy_img);
//        img.setImageBitmap(PictureUtil.getBitmap(dotStrategy.getPicture().getBitmapBytes()));

        TextView place = findViewById(R.id.other_doStrategy_place);
        place.setText(dotStrategy.getPlace_name());

        addToMyWishList = (Button)findViewById(R.id.add_to_my_wishlist);
        addToMyWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0) {
                    MyWish myWish = new MyWish("★", dotStrategy.getPlace_name());

                    Gson gson = new Gson();
                    try {
                        String temp=FileUtils.readFile(FileUtils.getRealPath()+"/SaveUser/myWishList.txt");
                        if(temp!="") {
                            List<MyWish> myWishList = gson.fromJson(temp, new TypeToken<List<MyWish>>() {
                            }.getType());//取出所有心愿
                            myWishList.add(myWish);
                            temp = gson.toJson(myWishList);
                        }
                        else
                        {
                            List<MyWish> myWishList = new ArrayList<>();
                            myWishList.add(myWish);//加入新的心愿
                            temp =gson.toJson(myWishList);
                        }

                        Log.d("otherdot11", myWish.getPlace());
                        FileUtils.saveFile(temp, "SaveUser", "myWishList.txt");//存入文件
                        Toast.makeText(OtherDotStrategyActivity.this, "添加心愿成功", Toast.LENGTH_SHORT).show();
                        flag = 1;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(OtherDotStrategyActivity.this,"已添加过此心愿",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



}
