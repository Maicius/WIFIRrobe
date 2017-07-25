package education.cs.scu.DAO;

import education.cs.scu.DBHelper.RedisPool;
import education.cs.scu.entity.User;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by maicius on 2017/7/25.
 */
public class LoginDAO {
    private static Jedis jedis;
    public User doUserLogin(User user) throws Exception {
        jedis = RedisPool.getJedis();

        Map<String, String> userMap = jedis.hgetAll(user.getUserName());
        User loginUser = new User();
        if (user.getPassword().equals(userMap.get("password")) &&
                user.getVerifyCode().equals(userMap.get("verifyCode"))) {
            //登陆成功返回用户名
            System.out.println("登陆成功");
            loginUser.setUserName(userMap.get("userName"));
            loginUser.setNickName(userMap.get("nickName"));
            //登陆成功清除验证码
            jedis.hset(loginUser.getUserName(), "verifyCode", "");
        }
        RedisPool.returnResource(jedis);
        return loginUser;
    }

    /**
     * 更新验证码
     * @param user
     * @return 返回值为int而不是Long是为了与mysql的返回值一致
     * @throws Exception
     */
    public int updateRedisVerifyCode(User user) throws Exception {
        jedis = RedisPool.getJedis();
        jedis.hset(user.getUserName(), "verifyTime", user.getVerifyTime());
        Long ret =  jedis.hset(user.getUserName(), "verifyCode", user.getVerifyCode());
        return ret.intValue();
        //user.setVerifyCode(String.valueOf(VerifyCodeUtil.createVerifyCode()));
    }
}
