package com.example.administrator.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.ActivityCollector;
import com.example.administrator.model.User;
import com.example.administrator.util.FileSaveUtils;
import com.example.administrator.util.GetUserInfomation;
import com.example.administrator.util.JsonAnalyze;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity{
    private String email = new String("");
    private String password = new String("");
    private int state = 3;
    private Button login;
    private  TextView t1;
    private  TextView t2;
    private CheckBox ck;
    private View progressBar;
    private User user;

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//从活动栈中删除活动
    }






    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollector.addActivity(this);



        TextView title = (TextView)findViewById(R.id.title_name);
        title.setText("登录账号");
        TextView forGetPwd = (TextView) findViewById(R.id.loginForgetPwd);
        forGetPwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(LoginActivity.this,ForgetPWDActivity.class);
                startActivity(intent);
            }
        });

        login = (Button)findViewById(R.id.sign_in);
        t1 = (TextView) findViewById(R.id.email);
        t2 =(TextView) findViewById(R.id.passwordL);
        ck = (CheckBox) findViewById(R.id.rememberPwd);
        progressBar = (ProgressBar)findViewById(R.id.login_progress);

        User userOld = GetUserInfomation.Get();
//        if(userOld.getUser_mail()!=null)
//        t1.setText(userOld.getUser_mail());

        String userResult = FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"/SaveUser/UserConfig.txt");
        Log.d("登录",userResult);
        if(userResult.length()!=0) {
            userOld=GetUserInfomation.Get();
            t1.setText(userOld.getUser_mail());
        }

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {



                email = t1.getText().toString();
                password = t2.getText().toString();

                if (t1.getText().length()==0 ||t2.getText().length()==0)
                {
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                 t1.setText(email);
//                 if(ck.isChecked())
//                 {
//
//                 }
                 progressBar.setVisibility(View.VISIBLE);//显示进度条

                 WorkThread sendMessage = new WorkThread();
                sendMessage.start();
                try {
                    sendMessage.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMessage.interrupt();

//                state = 0;
                switch (state){
                    case 0 :{

                        Intent intent = new Intent(LoginActivity.this,FragmentItemSetsActivity.class);
                        ActivityCollector.finishAll();
                        startActivity(intent);
                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case 1: {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        break;
                    }
                    case 2: {
                        Toast.makeText(LoginActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        break;
                    }
                    default:{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        break;
                    }
                }
                return;
            }
        });

    }


    private  class WorkThread extends Thread
    {
            @Override
            public void run() {
                try {
//                    OkHttpClient client = new OkHttpClient.Builder()
//                            .connectTimeout(10, TimeUnit.SECONDS)
//                            .writeTimeout(10, TimeUnit.SECONDS)
//                            .readTimeout(20, TimeUnit.SECONDS)
//                            .build();

                    JSONObject emailAndPwd = new JSONObject();
                    user = new User();
                    user.setUser_mail(email);
                    user.setUser_pwd(password);
                    ConnTool connTool = new ConnTool();
                    user=connTool.login(user);


//                    emailAndPwd.put("e-mail", email);
//                    emailAndPwd.put("userPwd", password);

//                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//                    RequestBody body = RequestBody.create(JSON, emailAndPwd.toString());
//
//                    Request request = new Request.Builder().url("http://115.159.198.216/YibuTest/Login").post(body).build();
//                    Response response = client.newCall(request).execute();

//                    String responseData = new String("");


//
//                    Log.d("登录",temp);
                    if (user.getUser_name()!=null) {
//                        responseData = response.body().string();
//
//                        String answer=JsonAnalyze.getJsonString(responseData);
                            state = 0;
                        Gson gson = new Gson();
                        String temp = gson.toJson(user);
                        Log.d("登录状态11",temp);
                        try {
//                            FileSaveUtils.saveFile("","","");
                            FileSaveUtils.saveFile(temp,"SaveUser","UserConfig.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                            state = 1;
                    //parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }


}







