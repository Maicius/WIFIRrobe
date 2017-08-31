package education.cs.scu.mapper.Impl;

import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.mapper.UserVisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * UserVisitMapperImpl
 *
 * @Author lch
 * @Create on 2017/08/25 19:51
 **/
public class UserVisitMapperImpl implements UserVisitMapper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String USER_VISIT_KEY = "USER_VISIT";

    public void addUserVisit(UserVisitBean userVisitBean) throws Exception {
        redisTemplate.opsForList().leftPush(USER_VISIT_KEY,userVisitBean);
    }
    /**
     *  写的有些问题，到时候讨论一下*/

    public UserFlow queryUserVisit() throws Exception {
        long size = redisTemplate.opsForList().size(USER_VISIT_KEY);
        return (UserFlow) redisTemplate.opsForList().rightPop(USER_VISIT_KEY);
    }
}
