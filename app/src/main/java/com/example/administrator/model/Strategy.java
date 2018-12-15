package com.example.administrator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Strategy implements Serializable {
    private Route route;
    private String comment;
    private String picture;
    private int num_likes;
    private Date publish_time;
    private String related_place;
    private List<DotStrategy> dotStrategy;
    private String title;
    //jingdu
    private double jing;
    //weidu
    private double wei;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
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

    public String getRelated_place() {
        return related_place;
    }

    public void setRelated_place(String related_place) {
        this.related_place = related_place;
    }






}
