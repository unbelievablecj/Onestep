package com.example.administrator.model;

import com.example.administrator.connect.PicJson;

import java.io.Serializable;

public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte[] bitmapBytes = null;

    public void setName(String name) {
        this.name = name;
    }

    private String name = null;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Picture(){

    }

    public Picture(byte[] bitmapBytes, String name) {

        this.bitmapBytes = bitmapBytes;

        this.name = name;
    }

    public byte[] getBitmapBytes() {
        return this.bitmapBytes;
    }

    public String getName() {
        return this.name;
    }


}
