package com.example.administrator.view;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.connect.ConnTool;
import com.example.administrator.model.User;
import com.example.administrator.util.GetUserInfomation;


//陈玮 修改密码界面
public class ChangePwdActivity extends AppCompatActivity {


    private EditText mail;
    private EditText newPassword;
    private EditText ver;
    private Button sendVer;
    private User oidUserPwd;
    private Button change;
    private  int state = -1;
    private  int state2 = -1;
    private TimeCount time;
    private boolean flagViewPwd = false;
    private Button canViewPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        TextView textView = (TextView)findViewById(R.id.title_name);
        sendVer = (Button) findViewById(R.id.changePwd_get_ver);
        textView.setText("修改密码");
         oidUserPwd =GetUserInfomation.Get();
        mail = (EditText)findViewById(R.id.changePwd_mail);
        ver =(EditText)findViewById(R.id.changePwd_ver);
        mail.setText(oidUserPwd.getUser_mail());
        change = (Button)findViewById(R.id.changePwd_finish);
        newPassword = (EditText) findViewById(R.id.changePwd_newPwd);
        canViewPwd =(Button)findViewById(R.id.can_view_pwd);

        time = new TimeCount(60000, 1000);//计时器，用来判断验证码发送间隔时间
        sendVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//发送验证码

                if(mail.getText().toString().length()==0)
                {
                    Toast.makeText(ChangePwdActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }


                time.start();
                WorkThread sendMessage = new WorkThread();//开线程发送验证码
                sendMessage.start();
                try {
                    sendMessage.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendMessage.interrupt();


            }
        });



        canViewPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//这是判断密码是否可见的按钮控制
                //按下按钮后
                if(flagViewPwd == false)//若密码不可见
                {
                    flagViewPwd = true;
                    canViewPwd.setBackground(getDrawable(R.drawable.viewpwd));
                    newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//设置密码可见

                }
                else
                {
                    flagViewPwd = false;
                    canViewPwd.setBackground(getDrawable(R.drawable.disview));
                    newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//设置密码不可见
                }

                newPassword.postInvalidate();
                CharSequence text = newPassword.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable)text;
                    Selection.setSelection(spanText, text.length());
                }//让光标到达末尾，不然每次切换密码可见性光标都会跳到开头


            }
        });



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               state = judgeInput();
               if(state==-1)
                   return;

               //开线程，发送当前信息改密码
                WorkThread2 toChangePWD = new WorkThread2();
                toChangePWD.start();
                try {
                    toChangePWD.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                toChangePWD.interrupt();

               // 1成功，0验证码错误，-1未知错误,-2用户不存在
                switch (state2)
                {
                    case 1:
                        Toast.makeText(ChangePwdActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        return;
                    case 0:
                        Toast.makeText(ChangePwdActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                        return;
                    case -2:
                        Toast.makeText(ChangePwdActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        return;

                    default:
                        Toast.makeText(ChangePwdActivity.this,"未知错误",Toast.LENGTH_SHORT).show();

                }
            }
        });

        }

        private int judgeInput()//判断输入合法性
        {
            if(mail.getText().toString().length()==0)
            {
                Toast.makeText(ChangePwdActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
                return -1;
            }
            else if(ver.getText().toString().length()==0)
            {
                Toast.makeText(ChangePwdActivity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                return -1;
            }
            else if(newPassword.getText().toString().length()==0)
            {
                Toast.makeText(ChangePwdActivity.this,"新密码不能为空",Toast.LENGTH_SHORT).show();
            }
            else if(newPassword.getText().toString().length()>16||newPassword.getText().toString().length()<6)
            {
                Toast.makeText(ChangePwdActivity.this,"密码长度在6~16位之间",Toast.LENGTH_SHORT).show();
            }
            return 0;
        }



    private  class WorkThread extends Thread//发送验证码线程
    {
        @Override
        public void run() {
            try {

                User userTemp = new User();
                userTemp.setUser_mail(mail.getText().toString());
                userTemp.setUser_pwd(null);
                userTemp.setUser_name(null);
                userTemp.setUser_ver(null);

                ConnTool connTool = new ConnTool();
                connTool.sendMail(userTemp);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    private  class WorkThread2 extends Thread//发送改密码用户信息线程
    {
        @Override
        public void run() {
            try {

                User userTemp = new User();
                userTemp.setUser_mail(mail.getText().toString());
                Log.d("修改密码",mail.getText().toString());
                userTemp.setUser_pwd(newPassword.getText().toString());
                userTemp.setUser_name(null);
                userTemp.setUser_ver(ver.getText().toString());

                ConnTool connTool = new ConnTool();
                state2 =connTool.changePwd(userTemp);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sendVer.setClickable(false);
            sendVer.setText("("+millisUntilFinished / 1000 +") ");
        }

        @Override
        public void onFinish() {
            sendVer.setText("获取验证码");
            sendVer.setClickable(true);

        }
    }


}

