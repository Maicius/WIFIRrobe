package education.cs.scu.service;

import education.cs.scu.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by maicius on 2017/3/31.
 */
public interface LoginService {
    User doUserLogin(User user) throws Exception;
    boolean verifyCode(User user, HttpServletRequest request) throws Exception;
    int updateVerifyCode(User user) throws Exception;
}
