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

    @RequestMapping(value="/userLogin", method= RequestMethod.POST)
    public User userLogin(HttpServletRequest request,
                             @RequestParam(value="username") String userName,
                             @RequestParam(value="password") String password) throws Exception{
        User user = new User(userName, password);
        System.out.println(userName);
        User loginUser = loginService.doUserLogin(user);
        HttpSession session = request.getSession();

        //获取当前系统时间，便于Monitor查询时间
        Timestamp timestamp =new Timestamp(System.currentTimeMillis());
        UserFlow userFlow = new UserFlow();
        userFlow.setTimestamp(timestamp);

        if(loginUser != null) {
            session.setAttribute("user", loginUser);
            System.out.println("finish:" + loginUser.getNickName());
            Monitor monitor = new Monitor();
            monitor.sendMsg();
            return loginUser;
        }else{
            User wrongUser = new User();
            wrongUser.setNickName("该用户不存在");
            session.setAttribute("user", wrongUser);
            return null;
        }
    }

    @RequestMapping(value="verifyCode", method = RequestMethod.GET)
    public String userLogin(HttpServletRequest request,
                            @RequestParam("userName") String userName) throws Exception{
        User user = new User();
        user.setUserName(userName);
        boolean res = loginService.verifyCode(user);
        if (res){
            return "success";
        }else{
            return "";
        }
    }

}
