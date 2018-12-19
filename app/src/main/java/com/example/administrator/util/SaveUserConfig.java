package com.example.administrator.util;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveUserConfig {

    public static void saveFile(String text, String path) throws IOException {

        String subForder = SAVE_REAL_PATH + path;
        File foder = new File(subForder);
        String fileName = "";
        if (!foder.exists()) {
            foder.mkdirs();
        }

        fileName = "UerConfig" + ".txt";

        File myCaptureFile = new File(subForder, fileName);

        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bos.write(text.getBytes());
        bos.flush();
        bos.close();

    }

    private static final String SAVE_USER_PATH=Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)?
            Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_USER_PATH+ "/onestep/dotStrategy/saveUser";//保存的确切位置

    public static String getRealPath()
    {
        return SAVE_REAL_PATH;
    }





}
