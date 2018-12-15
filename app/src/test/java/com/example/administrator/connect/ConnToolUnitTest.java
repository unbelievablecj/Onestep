package com.example.administrator.connect;
import com.example.administrator.model.User;
import org.junit.Test;
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
        user.setUser_mail("965412849@qq.com");
        user.setUser_pwd("123456");
        int res=ct.login(user);
        assertEquals(1, res);
    }
    @Test
    public void sendmail(){
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("965412849@qq.com");
        int res=ct.sendMail(user);
        assertEquals(1,res);
    }
}