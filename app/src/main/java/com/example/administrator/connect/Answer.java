package com.example.administrator.connect;
import com.google.gson.annotations.SerializedName;

public class Answer {
    @SerializedName("res")
    private String res;
    public String getRes() {
        return res;
    }
    public void setRes(String res) {
        this.res= res;
    }
}
