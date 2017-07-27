package education.cs.scu.mapper.Impl;

import education.cs.scu.DBHelper.RedisPool;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by maicius on 2017/7/26.
 */
public class UserMapperImpl implements UserMapper {
    private static Jedis jedis;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用户登陆类
     * 根据key值从Redis Hash中提取数据并进行验证
     * @param user
     * @return 带有用户昵称的用户对象
     * @throws Exception
     */
    public User doUserLogin2(User user) throws Exception{
        return null;
    }
    public User doUserLogin(User user) throws Exception {
        jedis = RedisPool.getJedis();
        User loginUser = new User();
        try {
            Map<String, String> userMap = jedis.hgetAll(user.getUserName());

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
        }catch(Exception e){
            //发生错误时强制关闭实例
            e.printStackTrace();
            if(jedis != null){
                RedisPool.returnResource(jedis);
            }
        }
        return loginUser;
    }

    /**
     * 更新验证码
     * @param user
     * @return 返回值为int而不是Long是为了与mysql的返回值一致
     * @throws Exception
     */
    public int updateVerifyCode(User user) throws Exception {
        jedis = RedisPool.getJedis();
        jedis.hset(user.getUserName(), "verifyTime", user.getVerifyTime());
        Long ret =  jedis.hset(user.getUserName(), "verifyCode", user.getVerifyCode());
        RedisPool.returnResource(jedis);
        return ret.intValue();
        //user.setVerifyCode(String.valueOf(VerifyCodeUtil.createVerifyCode()));
    }
}
