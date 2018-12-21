package com.example.administrator.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileSaveUtils{

    private static final String SAVE_USER_PATH=Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)?
            Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_USER_PATH+ "/onestep/";//保存的确切位置

    public static void saveFile(String text, String path, String fileName) throws IOException {

        String subForder = SAVE_REAL_PATH + path;
        Log.d("文件存取",SAVE_REAL_PATH);
        File foder = new File(subForder);
        if (!foder.exists()) {
            Log.d("文件存在",SAVE_REAL_PATH);


            foder.mkdirs();
        }

        File myCaptureFile = new File(subForder, fileName);

        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bos.write(text.getBytes());
        bos.flush();
        bos.close();

    }


    public static void saveFile(String text, String path) throws IOException {

        String subForder = SAVE_REAL_PATH + path;
        File foder = new File(subForder);
        if (!foder.exists()) {
            foder.mkdirs();
        }


        String fileName = FilenameUtil.createFileName(foder)+".txt";


        File myCaptureFile = new File(subForder, fileName);

        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bos.write(text.getBytes());
        bos.flush();
        bos.close();

    }

    public static String readFile( String strFilePath)  {


        String path = strFilePath;
        String result="";
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory())
        {
            Log.d("TestFile", "The File doesn't exist.");
        }
        else
        {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null)
                {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while (( line = buffreader.readLine()) != null) {
                        result+=line;
                    }
                    instream.close();
                }
            }
            catch (java.io.FileNotFoundException e)
            {
                Log.d("TestFile", "The File doesn't exist.");
            }
            catch (IOException e)
            {
                Log.d("TestFile", e.getMessage());
            }
        }
        return result;

    }

    public static void saveFileAdd(String text, String path, String fileName) throws IOException {

        String subForder = SAVE_REAL_PATH + path;
        Log.d("文件存取",SAVE_REAL_PATH);
        File foder = new File(subForder);
        if (!foder.exists()) {
            Log.d("文件存在",SAVE_REAL_PATH);


            foder.mkdirs();
        }




        File myCaptureFile = new File(subForder, fileName);

        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }

        FileOutputStream bos = new FileOutputStream(myCaptureFile,true);
        bos.write(text.getBytes());
        bos.flush();
        bos.close();

    }





    public static String getRealPath()
    {
        return SAVE_REAL_PATH;
    }





}
