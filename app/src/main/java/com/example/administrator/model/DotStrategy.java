package com.example.administrator.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

public class DotStrategy implements Serializable {
    //地点名
    private String place_name;
    //地点评论
    private String comment;
    //地点图片(只能放入一张)
    private Picture picture;
    //点赞数
    private int num_likes;
    //发表时间
    private Date publish_time;
    //纬度
    private Double Latitude;
    //经度
    private Double Longitude;

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
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

}
