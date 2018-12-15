package com.example.administrator.connect;

import com.google.gson.annotations.SerializedName;
//TODO 属性未完成
/**
 * 用于与服务器传输总攻略类，需要映射到Strategy类
 */
public class Gonglue {
    @SerializedName("Gid")
    private int Gid;
    @SerializedName("Gpoint")
    private String Gpoint;
    @SerializedName("Guser")
    private String Guser;
    @SerializedName("Gjing")
    private double Gjing;
    @SerializedName("Gwei")
    private double Gwei;
    @SerializedName("comment")
    private String comment;
    @SerializedName("picture")
    private String picture;
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getGid() {
        return Gid;
    }
    public void setGid(int gid) {
        Gid = gid;
    }
    public String getGpoint() {
        return Gpoint;
    }
    public void setGpoint(String gpoint) {
        Gpoint = gpoint;
    }
    public String getGuser() {
        return Guser;
    }
    public void setGuser(String guser) {
        Guser = guser;
    }
    public double getGjing() {
        return Gjing;
    }
    public void setGjing(double gjing) {
        Gjing = gjing;
    }
    public double getGwei() {
        return Gwei;
    }
    public void setGwei(double gwei) {
        Gwei = gwei;
    }


}