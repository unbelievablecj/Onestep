package com.example.administrator.connect;

import android.util.Log;

import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnTool {
    private String url="http://115.159.198.216/YibuTest/";
    private String Verified=url+"Verified";
    private String Login=url+"Login";
    private String Register=url+"Register";
    private String ChangePwd = url+"changePwd";
    private Gson g;
    private MediaType JSON;
    private OkHttpClient client;
    /**
     * 建立Client
     */
    public ConnTool(){
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        g=new Gson();
        JSON=MediaType.parse("application/json; charset=utf-8");
    }
    /**
     *
     * @param user
     * @return 0密码错误，1成功登陆，-1用户不存在
     */

    public int login(User user){
        return 0;
    }

    /**
     *
     * @param user
     * @return
     */
    public int register(User user){
        return 0;
    }

    /**
     * 发送验证码，邮箱存在User类
     * @param user 存邮箱
     * @return 1成功，0失败
     */
    public int sendMail(User user){
        try {
            String json=g.toJson(user);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(Verified).post(body).build();
            Response response = client.newCall(request).execute();
            Log.i("SendVer","response:body"+response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 验证码放在user里
     * @param user
     * @return
     */
    public int changePwd(User user){
        return 0;
    }

    /**
     * 1成功，0失败
     * @param strategy
     * @param user
     * @return
     */
    public int uploadStrategy(Strategy strategy, User user){
        return 0;
    }

    /**
     * 返回图片路径
     * @param fis
     * @return
     */
    public String uploadImage(FileInputStream fis){
        return "";
    }

    /**
     * 返回图片下载流
     * @param filename
     * @return
     */
    public FileOutputStream downloadImage(String filename) throws FileNotFoundException {
        return new FileOutputStream(new File(""));
    }

    /**
     *   如果没有这么多总攻略的话就只返回剩下的全部的
     *
     * @param jing 经度
     * @param wei 维度
     * @param begin begin从1开始
     * @param end
     * @return
     */
    public List<Strategy> discover(double jing, double wei, int begin, int end){
        List<Strategy> l=new ArrayList<Strategy>();
        return l;
    }
    /**
     *
     */


}
