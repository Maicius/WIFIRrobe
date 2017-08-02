package education.cs.scu.controller;

import education.cs.scu.entity.User;
import education.cs.scu.entity.UserFlow;
import education.cs.scu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**登陆控制类
 * 负责管理用户登陆时的验证信息
 * Created by maicius on 2017/3/31.
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 控制用户登登陆
     * @param request 保存session
     * @param userName
     * @param password
     * @param verifyCode
     * @return 返回用户昵称
     * @throws Exception
     */
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
            return loginUser;
        } else {
            //返回一个空对象
            System.out.println("failed to login");
            return new User();
        }
    }

    /**
     * 控制验证码发送
     * 根据用户名发送验证码
     * @param request
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "verifyCode", method = RequestMethod.GET)
    public User userVerify(HttpServletRequest request,
                            @RequestParam("userName") String userName) throws Exception {
        User user = new User();
        user.setUserName(userName);
        String res = loginService.verifyCode(user);
        if (res.length() > 0) {
            return user;
        } else {
            return new User();
        }
    }
}
