package com.example.administrator.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.Picture;
import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.example.administrator.util.FileSaveUtils;
import com.example.administrator.util.PictureUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

public class ShareSubmitActivity extends AppCompatActivity {

    private int labelNum;
    private static final String TAG = "ShareSubmitActivity";

    private Strategy strategy;
    private User user;


    public ShareSubmitActivity() {
        this.labelNum = 8;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_submit);
//        Button back = (Button)findViewById(R.id.title_button1);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShareSubmitActivity.this,FragmentItemSetsActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });

        Button commit = (Button)findViewById(R.id.tijiaofengxiang1);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShareSubmitActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(ShareSubmitActivity.this,FragmentItemSetsActivity.class);


                EditText title = findViewById(R.id.strategyTitle);
                CheckBox checkBox_food = findViewById(R.id.label_food);
                CheckBox checkBox_scene = findViewById(R.id.label_scene);
                CheckBox checkBox_countryside = findViewById(R.id.label_countryside);
                CheckBox checkBox_city = findViewById(R.id.label_city);
                CheckBox checkBox_oneDay = findViewById(R.id.label_oneDay);
                CheckBox checkBox_days = findViewById(R.id.label_days);
                CheckBox checkBox_group = findViewById(R.id.label_group);
                CheckBox checkBox_single = findViewById(R.id.label_single);
                String labelContent="";
                strategy = (Strategy)getIntent().getSerializableExtra("strategy_data");


                if(checkBox_food.isChecked()){
                    labelContent+=checkBox_food.getText().toString();

                }
                if(checkBox_scene.isChecked()){
                    labelContent+=checkBox_scene.getText().toString();

                }
                if(checkBox_countryside.isChecked()){
                    labelContent+=checkBox_countryside.getText().toString();

                }
                if(checkBox_city.isChecked()){
                    labelContent+=checkBox_city.getText().toString();

                }
                if(checkBox_oneDay.isChecked()){
                    labelContent+=checkBox_oneDay.getText().toString();

                }
                if(checkBox_days.isChecked()){
                    labelContent+=checkBox_days.getText().toString();

                }
                if(checkBox_group.isChecked()){
                    labelContent+=checkBox_group.getText().toString();

                }
                if(checkBox_single.isChecked()){
                    labelContent+=checkBox_single.getText().toString();

                }

                Gson gson = new Gson();
                strategy.setTitle(title.getText().toString());
                strategy.setLabel(labelContent);


                Bitmap b = PictureUtil.compressSampling("savePic201812200612364251.JPEG");
                Picture picture = new Picture(PictureUtil.getBytes(b),"saveaa");

                strategy.setPicture(picture);


                try {
                    FileSaveUtils.saveFile(gson.toJson(strategy),"strategy");
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String userResult = FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"/SaveUser/UserConfig.txt");

                user = gson.fromJson(userResult,User.class);


                ShareSubmitActivity.WorkThread sendMessage = new ShareSubmitActivity.WorkThread();
                sendMessage.start();
                try {
                    sendMessage.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMessage.interrupt();





                startActivity(intent);
                finish();
            }
        });

    }


    private  class WorkThread extends Thread
    {
        @Override
        public void run() {
            try {


                JSONObject emailAndPwd = new JSONObject();


                ConnTool connTool = new ConnTool();
                int result = connTool.uploadStrategy(strategy,user);
                Log.i(TAG, "结果："+result);

                //parseJSONWithJSONObject(responseData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
