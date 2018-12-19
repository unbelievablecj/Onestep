package com.example.administrator.connect;
import com.example.administrator.model.User;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConnToolUnitTest {
    @Test
    public void login1() {
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("test");
        user.setUser_pwd("test");
        User user1=ct.login(user);
        System.out.print(user1.getUser_pwd());
    }
    @Test
    public void sendmail(){
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("965412849@qq.com");
        int res=ct.sendMail(user);
        assertEquals(1,res);
    }
    @Test
    public void downloadImage(){
        File file=new File("D://down.jpg");

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos=new FileOutputStream(file);
            ConnTool ct=new ConnTool();
            byte[] b=ct.downloadImage("1545059020332.jpg");
            if(b!=null){
                fos.write(b);
                fos.flush();
                fos.close();
            }
            else{
                System.out.println("null");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void uploadImage(){
        File file=new File("D://java.jpg");
        ConnTool ct=new ConnTool();
        String res=ct.uploadImage(file);
        if(res!=null){
            System.out.println(res);
        }
        else{
            System.out.println("fail");
        }
    }
}