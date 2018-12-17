package com.example.administrator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Strategy implements Serializable {

    //路线类
    private Route route;
    //总攻略的评论
    private String comment;

    //总攻略的图片(只有一张)
    private Picture picture;
    //总攻略的点赞数
    private int num_likes;
    //总攻略的发表时间
    private Date publish_time;
    //地点攻略
    private List<DotStrategy> dotStrategy;
    //攻略标题
    private String title;

    public Point getFeat_LatLng() {
        return feat_LatLng;
    }

    public void setFeat_LatLng(Point feat_LatLng) {
        this.feat_LatLng = feat_LatLng;
    }

    private Point feat_LatLng;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String label;


    public List<DotStrategy> getDotStrategy() {
        return dotStrategy;
    }

    public void setDotStrategy(List<DotStrategy> dotStrategy) {
        this.dotStrategy = dotStrategy;
    }



    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNum_likes() {
        return num_likes;
    }

    public void setNum_likes(int num_likes) {
        this.num_likes = num_likes;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }






}
