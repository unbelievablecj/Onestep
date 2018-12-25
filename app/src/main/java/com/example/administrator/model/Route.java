package com.example.administrator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.amap.api.maps.*;
import com.amap.api.maps.model.LatLng;
/**
 * @date: 2018/12/25
 * @author: wyz
 * @version:
 * @description: 路线类，用于存储点集以及总的路线长度
 */

public class Route implements Serializable {

    private List<Point> points;    //路线上的点集列表
    private Double total_distance;    //路线长度


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
