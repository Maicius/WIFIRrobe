package education.cs.scu.controller;

import education.cs.scu.entity.User;
import education.cs.scu.entity.UserFlow;
import education.cs.scu.service.LoginService;
import education.cs.scu.webSocket.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by maicius on 2017/3/31.
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public User userLogin(HttpServletRequest request,
                          @RequestParam(value = "userName") String userName,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "verifyCode") String verifyCode) throws Exception {
        HttpSession session = request.getSession();
        System.err.println("login session");
        System.err.println(session);

        User user = new User(userName, password, verifyCode);
        System.err.println(userName);
        User loginUser = loginService.doUserLogin(user);
        //获取当前系统时间，便于Monitor查询时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserFlow userFlow = new UserFlow();
        userFlow.setTimestamp(timestamp);

        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            System.out.println("finish:" + loginUser.getNickName());
            Monitor monitor = new Monitor();
            monitor.sendMsg();
            //登陆成功之后随机赋予验证码新值，避免重复登陆
            int res = loginService.updateVerifyCode(user);
            if (res >0 )
                System.out.println("验证码已更新");
            return loginUser;
        } else {
            //返回一个空对象
            return new User();
        }
    }

    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public User userLogin(HttpServletRequest request,
                            @RequestParam("userName") String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        boolean res = loginService.verifyCode(user, request);
        if (res) {
            return user;
        } else {
            return new User();
        }
    }

}
