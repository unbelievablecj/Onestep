package com.example.administrator.model;

import java.io.Serializable;

public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte[] bitmapBytes = null;
    private String name = null;

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
