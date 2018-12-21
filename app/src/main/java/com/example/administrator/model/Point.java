package com.example.administrator.model;

import java.io.Serializable;

public class Point implements Serializable {
    //纬度
    private Double Latitude;
    //经度
    private Double Longitude;


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
