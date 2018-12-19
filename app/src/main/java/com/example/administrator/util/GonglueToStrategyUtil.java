package com.example.administrator.util;
import com.example.administrator.connect.Gonglue;
import com.example.administrator.model.Picture;
import com.example.administrator.model.Strategy;
import com.example.administrator.model.Route;
import com.example.administrator.model.DotStrategy;
import com.google.gson.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.administrator.connect.ConnTool;
import com.google.gson.reflect.TypeToken;

public class GonglueToStrategyUtil {
    /**
     * 对象转换
     *将Gonglue转为Strategy
     *
     */
    ConnTool conntool=new ConnTool();


//传入Gonglue对象，转化为Strategy对象
    public Strategy GonglueToStrategy(Gonglue gl)
    {
        Strategy st=new Strategy();
        st.setComment(gl.getComment());

            Gson gson1 = new Gson();
        List<DotStrategy> temp = gson1.fromJson(gl.getDotStrategy(), new TypeToken<List<DotStrategy>>() {}.getType());
            st.setDotStrategy(temp);


        st.setNum_likes(Integer.parseInt(gl.getNum_likes()));
        Picture picture=null;
        try{
             picture=new Picture(conntool.downloadImage(gl.getPicture()),"");
        }catch (IOException e){
            e.printStackTrace();
        }
        st.setPicture(picture);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try{
            date=format.parse(gl.getPublish_time());
        }catch (ParseException e){
            e.printStackTrace();
        }
        st.setPublish_time(date);
        Gson gson2=new Gson();
        Route rt2=gson2.fromJson(gl.getRoute(),Route.class);
        st.setRoute(rt2);
        st.setTitle(gl.getTitle());
        return st;
    }
}
