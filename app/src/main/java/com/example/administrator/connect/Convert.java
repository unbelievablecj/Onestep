package com.example.administrator.connect;

import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.Picture;
import com.example.administrator.model.Point;
import com.example.administrator.model.Route;
import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Convert {
    public static Gonglue StrategyToGonglue(Strategy strategy, User user){
        Gonglue gl=new Gonglue();
        gl.setGuser(user.getUser_name());
        gl.setComment(strategy.getComment());
        Gson g = new Gson();
        List<DotStrategy> ds=strategy.getDotStrategy();
        String toString1 = g.toJson(ds);
        gl.setDotStrategy(toString1);
        Gson gson2 = new Gson();
        Route rt=strategy.getRoute();
        String toString2=gson2.toJson(rt);
        gl.setRoute(toString2);
        gl.setComment(strategy.getComment());
        gl.setNum_likes(String.valueOf(strategy.getNum_likes()));
        gl.setPicture("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        gl.setPublish_time(sdf.format(strategy.getPublish_time()));
        gl.setLatitude(strategy.getFeat_LatLng().getLatitude());
        gl.setLongitude(strategy.getFeat_LatLng().getLongitude());
        gl.setTitle(strategy.getTitle());
        return gl;
    }
    public static Strategy GonglueToStrategy(Gonglue gl){
        Strategy st=new Strategy();
        st.setComment(gl.getComment());
        st.setId(gl.getGid());
        st.setNum_likes(Integer.parseInt(gl.getNum_likes()));
        st.setLabel(gl.getLabel());
        st.setTitle(gl.getTitle());
        st.setFeat_LatLng(new Point(gl.getLatitude(),gl.getLongitude()));
        Gson g= new Gson();
        List<DotStrategy> temp = g.fromJson(gl.getDotStrategy(), new TypeToken<List<DotStrategy>>() {}.getType());
        st.setDotStrategy(temp);
        Picture picture=null;
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
