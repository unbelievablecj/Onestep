package com.example.administrator.util;

import com.example.administrator.model.User;
import com.google.gson.Gson;
//陈玮
public class GetUserInfomation {//从文件中取用户信息

    public static User Get()
    {
        String userResult = FileUtils.readFile(FileUtils.getRealPath()+"/SaveUser/UserConfig.txt");
        Gson gson = new Gson();
        User user = gson.fromJson(userResult,User.class);
        return user;
    }
}
