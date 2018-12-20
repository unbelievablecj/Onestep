package com.example.administrator.connect;

import com.example.administrator.model.DotStrategy;
import com.example.administrator.model.Picture;
import com.example.administrator.model.Point;
import com.example.administrator.model.Route;
import com.example.administrator.model.Strategy;
import com.example.administrator.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Convert {
    public static Gonglue StrategyToGonglue(Strategy strategy, User user){
        Gonglue gl=new Gonglue();
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        gl.setGid(strategy.getId());
        gl.setGuser(user.getUser_mail());
        gl.setComment(strategy.getComment());
        PicJson pj=new PicJson();
        pj.setName(strategy.getPicture().getName());
        pj.setUrl(strategy.getPicture().getUrl());
        gl.setPicture(g.toJson(pj));
        gl.setRoute(g.toJson(strategy.getRoute()));
        gl.setNum_likes(Integer.toString(strategy.getNum_likes()));
        gl.setPublish_time(g.toJson(strategy.getPublish_time()));
        gl.setTitle(strategy.getTitle());
        gl.setLatitude(strategy.getFeat_LatLng().getLatitude());
        gl.setLongitude(strategy.getFeat_LatLng().getLongitude());
        gl.setLabel(strategy.getLabel());
        gl.setDotStrategy(g.toJson(strategy.getDotStrategy()));
        return gl;
    }
    public static Strategy GonglueToStrategy(Gonglue gl){
        Strategy st=new Strategy();
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        st.setComment(gl.getComment());
        st.setId(gl.getGid());
        st.setNum_likes(Integer.parseInt(gl.getNum_likes()));
        st.setLabel(gl.getLabel());
        st.setTitle(gl.getTitle());
        st.setFeat_LatLng(new Point(gl.getLatitude(),gl.getLongitude()));
        st.setRoute(g.fromJson(gl.getRoute(),Route.class));
        PicJson pj=new PicJson();
        pj=g.fromJson(gl.getPicture(),PicJson.class);
        st.setPicture(new Picture());
        st.getPicture().setUrl(pj.getUrl());
        st.getPicture().setName(pj.getName());
        st.setPublish_time(g.fromJson(gl.getPublish_time(),Date.class));
        Type type = new TypeToken<List<DotStrategy>>() {
        }.getType();
        List<DotStrategy> ls=g.fromJson(gl.getDotStrategy(),type);
        st.setDotStrategy(ls);
        return st;

    }
}
