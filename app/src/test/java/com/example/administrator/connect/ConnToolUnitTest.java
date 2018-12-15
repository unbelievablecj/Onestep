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
    public void login() {
        ConnTool ct=new ConnTool();
        User user=new User();
        user.setUser_mail("96541249@qq.com");
        user.setUser_pwd("123456");
        int res=ct.login(user);
        assertEquals(1, res);
    }
}