package com.example.administrator.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.ActivityCollector;
import com.example.administrator.model.User;
import com.example.administrator.util.FileUtils;
import com.example.administrator.util.AesUtil;
import com.example.administrator.util.GetUserInfomation;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

//登录界面 陈玮

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
    private SharedPreferences sp;//保存boolean状态，记住密码框的点选状态


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
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        User userOld = GetUserInfomation.Get();
//        if(userOld.getUser_mail()!=null)
//        t1.setText(userOld.getUser_mail());

        String userResult = FileUtils.readFile(FileUtils.getRealPath()+"/SaveUser/UserConfig.txt");
        Log.d("登录",userResult);
        if(userResult.length()!=0) { //用户配置文件不为空
            userOld=GetUserInfomation.Get();
            t1.setText(userOld.getUser_mail());
            if(sp.getBoolean("ISCHECK", false))//记住密码框被点中
            {
                ck.setChecked(true);
//                try {
//                   userOld.setUser_pwd(AesUtil.decrypt("abc",userOld.getUser_pwd()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                t2.setText(userOld.getUser_pwd());
            }

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
                 progressBar.setVisibility(View.VISIBLE);//显示进度条

                //开线程发送用户数据和服务器端匹配后登录
                 WorkThread sendMessage = new WorkThread();
                sendMessage.start();
                try {
                    sendMessage.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMessage.interrupt();

//                state = 0;
                switch (state){ //登录的错误判断
                    case 0 :{

                        Intent intent = new Intent(LoginActivity.this,FragmentItemSetsActivity.class);
                        ActivityCollector.finishAll();//结束栈中所有活动打开主界面
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



        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) { // 检查记住密码框的点选状态，写入sp
                if (ck.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }

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
                    if (!user.getUser_name().equals(null)) {
//                        responseData = response.body().string();
//
//                        String answer=JsonAnalyze.getJsonString(responseData);
                            state = 0;
                        Gson gson = new Gson();
//                        user.setUser_pwd(AesUtil.encrypt("abc",user.getUser_pwd()));//密码进行Aes加密
                        Log.d("登录加密","执行过这里");
                        String temp = gson.toJson(user);

                        try {
//                            FileUtils.saveFile("","","");
                            FileUtils.saveFile(temp,"SaveUser","UserConfig.txt");
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







