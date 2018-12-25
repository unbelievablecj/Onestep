package com.example.administrator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: 总攻略类
 */
public class Strategy implements Serializable {

    private int id;    //总攻略id
    private Route route;    //路线类
    private String comment;    //总攻略的评论
    private Picture picture;    //总攻略的图片(只有一张)
    private int num_likes;    //总攻略的点赞数
    private Date publish_time;    //总攻略的发表时间
    private List<DotStrategy> dotStrategy;    //地点攻略
    private String title;    //攻略标题
    private String label;    //标签
    private Point feat_LatLng;    //经纬度


    public Point getFeat_LatLng() {
        return feat_LatLng;
    }

    public void setFeat_LatLng(Point feat_LatLng) {
        this.feat_LatLng = feat_LatLng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
