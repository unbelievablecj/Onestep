package com.example.administrator.model;

import java.io.Serializable;
/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: 点类 用于存经纬度信息
 */


public class Point implements Serializable {
    private Double Latitude;    //纬度
    private Double Longitude;    //经度


    public Point(Double latitude, Double longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

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




}
