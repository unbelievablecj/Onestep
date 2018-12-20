package com.example.administrator.connect;
import android.util.Log;

import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConnToolUnitTest {
    @Test
    public void login1() {
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("test");
        user.setUser_pwd("test");
        User user1=ct.login(user);
        if(user1.getUser_mail()!=null)
            assertEquals("test",user1.getUser_mail());
    }
    @Test
    public void login2(){
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("test");
        user.setUser_pwd("wrong");
        User user1=ct.login(user);
        assertEquals(null,user1);
    }
    @Test
    public void register(){
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("965412849@qq.com");
        user.setUser_pwd("123456");
        //验证码根据后台更改
        user.setUser_ver("713594");
        int res=ct.register(user);
        assertEquals(-1,res);
    }
    @Test
    public void sendmail(){
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("965412849@qq.com");
        int res=ct.sendMail(user);
        assertEquals(1,res);
    }
    @Test
    public void downloadImage(){
        File file=new File("D://down.jpg");

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos=new FileOutputStream(file);
            ConnTool ct=new ConnTool();
            byte[] b=ct.downloadImage("1545059020332.jpg");
            if(b!=null){
                fos.write(b);
                fos.flush();
                fos.close();
            }
            else{
                System.out.println("null");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void uploadImage(){
        File file=new File("D://java.jpg");
        ConnTool ct=new ConnTool();
        String res=ct.uploadImage(file);
        if(res!=null){
            System.out.println(res);
        }
        else{
            System.out.println("fail");
        }
    }
    @Test
    public void uploadStrategy(){
        OkHttpClient client;
        MediaType JSON;
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        JSON=MediaType.parse("application/json; charset=utf-8");
        Gonglue gl= new Gonglue();
        gl.setTitle("t");
        try{
            Gson g=new Gson();
            String json=g.toJson(gl);
            RequestBody body=RequestBody.create(JSON, json);
            Request request = new Request.Builder().url("http://115.159.198.216/YibuTest/ShareUpdown").post(body).build();
            Response response = client.newCall(request).execute();
            Answer a= g.fromJson(response.body().string(),Answer.class);
            assertEquals("success",a.getRes());
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }

    }
}