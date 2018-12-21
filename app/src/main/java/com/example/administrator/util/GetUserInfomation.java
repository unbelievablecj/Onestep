package com.example.administrator.util;

import com.example.administrator.model.User;
import com.google.gson.Gson;

public class GetUserInfomation {

    public static User Get()
    {
        String userResult = FileSaveUtils.readFile(FileSaveUtils.getRealPath()+"/SaveUser/UserConfig.txt");
        Gson gson = new Gson();
        User user = gson.fromJson(userResult,User.class);
        return user;
    }
}
