package com.example.administrator.model;

public class MyWish {
    private String place;//地点
    private String serialNumber;//序号
//    private int imageOne;//地点图标
//    private int imageTwo;//删除图标


    public MyWish(String n, String p)
    {
        this.serialNumber=n;
        this.place=p;
//        this.imageOne = i;
//        this.imageTwo = j;
    }

    public String getPlace()
    {
        return place;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

//    public int getImageOneID()
//    {
//        return imageOne;
//    }
//
//    public int getImageTwoID()
//    {
//        return imageTwo;
//    }

}
