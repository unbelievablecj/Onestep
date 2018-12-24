package com.example.administrator.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.model.ActivityCollector;

public class SettingsActivity extends AppCompatActivity {


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//从活动栈中删除活动
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActivityCollector.addActivity(SettingsActivity.this);
        TextView textView = (TextView)findViewById(R.id.title_name);
        textView.setText("设置");
        Button outLogin = (Button)findViewById(R.id.outLogin);
        Button changePwd = (Button)findViewById(R.id.changePwd);
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                ActivityCollector.finishAll();
                startActivity(intent);
            }
        });
        changePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,ChangePwdActivity.class);
                startActivity(intent);
            }
        });
    }
}
