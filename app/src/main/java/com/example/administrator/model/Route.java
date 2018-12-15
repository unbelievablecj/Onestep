package com.example.administrator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.amap.api.maps.*;
import com.amap.api.maps.model.LatLng;


public class Route implements Serializable {


    private List<Point> points;
    private Double total_distance;
    private Point feat_LatLng;

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

    public Point getFeat_LatLng() {
        return feat_LatLng;
    }

    public void setFeat_LatLng(Point feat_LatLng) {
        this.feat_LatLng = feat_LatLng;
    }



}
