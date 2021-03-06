package com.example.administrator.connect;
import android.bluetooth.BluetoothAssignedNumbers;
import android.util.Log;

import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.Route;
import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.text.SimpleDateFormat;

public class ConnTool {

    private static String TAG = "ConnTool";
    private String url="http://115.159.198.216/YibuTest/";
    //private String url="http://127.0.0.1/YibuTest/";
    private String verifiedUrl=url+"Verified";
    private String loginUrl=url+"Login";
    private String registerUrl=url+"Register";
    private String changePwdUrl = url+"ChangePwd";
    private String uploadImageUrl =url+"PicUpload";
    private String downImageUrl=url+"PicDownload";
    private String discoverUrl=url+"SearchNear";
    private String uploadStrategy=url+"ShareUpdown";
    private String changeStrategyUrl=url+"ChangeShare";
    private Gson g;
    private MediaType JSON;
    private OkHttpClient client;
    /**
     * 建立Client
     */
    public ConnTool()
    {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        g=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JSON=MediaType.parse("application/json; charset=utf-8");
    }
    /**
     *
     * @param user 传输用户的邮箱和密码
     * @return 完整的user，null表示登陆失败
     */

    public User login(User user)
    {

        try {
            String json=g.toJson(user);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(loginUrl).post(body).build();
            Response response = client.newCall(request).execute();
            String temp=response.body().string();
            User u= g.fromJson(temp,User.class);
            if(u==null)return null;
            if(u.getUser_mail()==null||u.getUser_mail().equals(""))return null;
            return u;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param user 存放用户的邮箱和填写的验证码
     * @return 1成功，0验证码错误，-1用户已存在,-2未知错误
     */
    public int register(User user)
    {
        try {
            String json=g.toJson(user);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(registerUrl).post(body).build();
            Response response = client.newCall(request).execute();
            Answer a= g.fromJson(response.body().string(),Answer.class);
            if(a.getRes().equals("Yes"))return 1;
            else if(a.getRes().equals("User_Exist"))return -1;
            else if(a.getRes().equals("Ver_Wrong"))return 0;
            else return -2;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        }
    }

    /**
     * 发送验证码，邮箱存在User类
     * @param user 存邮箱
     * @return 1成功，0失败，-1未知错误
     */
    public int sendMail(User user)
    {
        try {
            String json=g.toJson(user);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(verifiedUrl).post(body).build();
            Response response = client.newCall(request).execute();
            Answer a=g.fromJson(response.body().string(),Answer.class);
            if(a.getRes().equals("success"))return 1;
            else return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     *
     * @param user 存放验证码，用户邮箱和想要修改的密码
     * @return 1成功，0验证码错误，-1未知错误,-2用户不存在
     */
    public int changePwd(User user)
    {
        try {
            String json=g.toJson(user);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(changePwdUrl).post(body).build();
            Response response = client.newCall(request).execute();
            Answer a=g.fromJson(response.body().string(),Answer.class);
            if(a.getRes().equals("Ver_Wrong"))return 0;
            else if(a.getRes().equals("User_Not_Exist"))return -2;
            else if(a.getRes().equals("success"))return 1;
            else return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 将strategy和user和为Gonglue类后再转为json传输
     * @param strategy 要上传的总攻略类
     * @param user 当前登陆的用户
     * @return 1成功，0未知错误,-1数据库错误
     */
    public int uploadStrategy(Strategy strategy, User user)
    {

        Gonglue gl= Convert.StrategyToGonglue(strategy,user);
        try{

           String json=g.toJson(gl);
            Log.i(TAG, json);
           RequestBody body=RequestBody.create(JSON, json);
           Request request = new Request.Builder().url(uploadStrategy).post(body).build();
           Response response = client.newCall(request).execute();
           Answer a= g.fromJson(response.body().string(),Answer.class);
           if(a.getRes().equals("success"))
               return 1;
           else if(a.getRes().equals("sql_wrong"))
               return -1;
           else
               return 0;
           }
           catch (IOException e)
           {
               e.printStackTrace();
               return 0;
           }


    }

    /**
     *
     * @param file 要传输的图片文件
     * @return 图片路径，不带ip
     */
    public String uploadImage(File file)
    {
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "", image)
                .build();
        Request request = new Request.Builder()
                .url(uploadImageUrl)
                .post(requestBody)
                .build();

        try {
            Response response = null;
            response = client.newCall(request).execute();
            Answer a=g.fromJson(response.body().string(),Answer.class);
            return a.getRes();
        } catch (IOException e) {
            Log.i("uploadImage","uploadException");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param imageUrl，不带ip
     * @return 图片的byte数组,错误的话返回null
     */
    public byte[] downloadImage(String imageUrl) throws FileNotFoundException
    {
        Request request = new Request.Builder()
                .url(url+imageUrl)
                .build();
        byte[] bytes;
        try {
            Response response = client.newCall(request).execute();
            bytes = response.body().bytes();
            return bytes;
        } catch (IOException e) {
            Log.i("downloadImage","ioException");
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param jing 经度
     * @param wei 维度
     * @param begin begin从1开始
     * @param end 结束位置
     * @return 返回从begin开始到end结束的总攻略类，数量不足返回剩余全部，begin越界放回空串,出现错误返回null
     */
    public List<Strategy> discover(double jing, double wei, int begin, int end)
    {
        Ask ask=new Ask();
        ask.setBegin(begin);
        ask.setEnd(end);
        ask.setJing(jing);
        ask.setWei(wei);
        List<Gonglue> l;
        List<Strategy> res=new ArrayList<Strategy>();
        try {
            String json=g.toJson(ask);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(discoverUrl).post(body).build();
            Response response = client.newCall(request).execute();
            Answer a=g.fromJson(response.body().string(),Answer.class);
            if(a.getRes().equals("sql_wrong")) {
                return null;
            }
            Type type = new TypeToken<List<Gonglue>>() {
            }.getType();
            l=g.fromJson(a.getRes(),type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        for(int i=0;i<l.size();i++){
            res.add(Convert.GonglueToStrategy(l.get(i)));
        }
        return res;
    }

    /**
     *
     * @param s
     * @param user
     * @return 1成功，0失败
     */
    public int changeStrategy(Strategy s,User user){
        Gonglue gl=Convert.StrategyToGonglue(s,user);
        try {
            String json=g.toJson(gl);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(changeStrategyUrl).post(body).build();
            Response response = client.newCall(request).execute();
            Answer a=g.fromJson(response.body().string(),Answer.class);
            if(a.getRes().equals("success"))return 1;
            else return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


}
