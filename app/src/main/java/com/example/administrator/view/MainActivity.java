package com.example.administrator.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.R;

import pub.devrel.easypermissions.EasyPermissions;

import static com.example.administrator.util.Constants.RC_CAMERA__CALENDAR_STORAGE_PHONE_LOCATION;


public class MainActivity extends AppCompatActivity {
    private String perms[]= {
        Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //判断有没有权限
        if (EasyPermissions.hasPermissions(MainActivity.this, perms)) {
            //

        } else {

            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是申请的权限
            EasyPermissions.requestPermissions(this, "需要申请您手机的读写文件权限", RC_CAMERA__CALENDAR_STORAGE_PHONE_LOCATION, perms);

        }



        Button login = (Button)findViewById(R.id.main_button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (perms[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) || perms[1].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                    Toast.makeText(MainActivity.this, "读写权限获取失败", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Button register = (Button)findViewById(R.id.main_button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



}
