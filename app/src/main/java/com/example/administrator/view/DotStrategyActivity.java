package com.example.administrator.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.DotStrategy;
import com.example.administrator.util.FileUtils;
import com.example.administrator.util.PictureUtil;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: 地点攻略页面类
 */

public class DotStrategyActivity extends AppCompatActivity {

    private DotStrategy dotStrategy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Gson gson = new Gson();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_strategy);
        String string =(String)getIntent().getSerializableExtra("dotStrategyDetail");
        String s = FileUtils.readFile(FileUtils.getRealPath()+"dotStrategy/"+string+".txt");
        dotStrategy = gson.fromJson(s,DotStrategy.class);
        init();



    }

    /**
     * 初始化页面信息
     */
    public void init(){

        TextView time = findViewById(R.id.doStrategy_time);
        DateFormat format = new SimpleDateFormat("yyyy MM-dd HH:mm:ss");
        time.setText(format.format(dotStrategy.getPublish_time()));

        TextView text = findViewById(R.id.doStrategy_text);
        text.setText(dotStrategy.getComment());


        if(dotStrategy.getPicture()!=null){
            ImageView img = findViewById(R.id.doStrategy_img);
            img.setImageBitmap(PictureUtil.getBitmap(dotStrategy.getPicture().getBitmapBytes()));
        }


        TextView place = findViewById(R.id.doStrategy_place);
        place.setText(dotStrategy.getPlace_name());

    }



}
