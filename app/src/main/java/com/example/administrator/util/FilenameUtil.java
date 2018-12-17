package com.example.administrator.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FilenameUtil {

    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }

    public static String createFileName(File from) {
        String[] fileInfo = getFileInfo(from);
        String toPrefix = fileInfo[0] + generateSuffix();
        String toSuffix = fileInfo[1];
        return toPrefix + toSuffix;
    }

    /**
     * @param from fileInfo[0]: toPrefix;
     *             fileInfo[1]:toSuffix;
     * @return
     */
    public static String[] getFileInfo(File from) {
        String fileName = from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix = "";
        String toSuffix = "";
        if (index == -1) {
            toPrefix = fileName;
        } else {
            toPrefix = fileName.substring(0, index);
            toSuffix = fileName.substring(index, fileName.length());
        }
        return new String[]{toPrefix, toSuffix};
    }
}
