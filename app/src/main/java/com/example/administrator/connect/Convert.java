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
import java.util.ArrayList;
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

        List<DotStrategy> ls=strategy.getDotStrategy();
        List<DotJson> dotjson=new ArrayList<DotJson>();
        DotJson dj;
        for(int i=0;i<ls.size();i++){
            dj=new DotJson();
            dj.setComment(ls.get(i).getComment());
            dj.setNum_likes(ls.get(i).getNum_likes());
            dj.setPicture(new PicJson());
            dj.getPicture().setName(ls.get(i).getPicture().getName());
            dj.getPicture().setUrl(ls.get(i).getPicture().getUrl());
            dj.setPlace_name(ls.get(i).getPlace_name());
            dj.setPublish_time(ls.get(i).getPublish_time());
            dotjson.add(dj);
        }
        gl.setDotStrategy(g.toJson(dotjson));
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
        Type type = new TypeToken<List<DotJson>>() {
        }.getType();
        List<DotJson> ls=g.fromJson(gl.getDotStrategy(),type);
        List<DotStrategy> res=new ArrayList<DotStrategy>();
        DotStrategy ds;
        for(int i=0;i<ls.size();i++){
            ds=new DotStrategy();
            ds.setComment(ls.get(i).getComment());
            ds.setNum_likes(ls.get(i).getNum_likes());
            ds.setPicture(new Picture());
            ds.getPicture().setName(ls.get(i).getPicture().getName());
            ds.getPicture().setUrl(ls.get(i).getPicture().getUrl());
            ds.setPlace_name(ls.get(i).getPlace_name());
            ds.setPublish_time(ls.get(i).getPublish_time());
            res.add(ds);
        }
        st.setDotStrategy(res);
        return st;

    }
}
