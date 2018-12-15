package com.example.administrator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.amap.api.maps.*;
import com.amap.api.maps.model.LatLng;


public class Route implements Serializable {

    //路线上的点集列表
    private List<Point> points;
    //路线长度
    private Double total_distance;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Double getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(Double total_distance) {
        this.total_distance = total_distance;
    }



}
