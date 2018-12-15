package com.example.administrator.connect;

import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ConnTool {
    /**
     *
     */
    public ConnTool(){

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
     *
     * @param user
     * @return
     */
    public int sendMail(User user){
        return 0;
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
