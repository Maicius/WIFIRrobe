package education.cs.scu.tool;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Redis测试类，主要验证一些redis操作。
 *
 * @Author lch
 * @Create on 2017/08/25 00:56
 **/
@Repository("testRedis")
public class RedisTest {

    @Autowired
    RedisTemplate<String, ProbeUser> redisTemplate;
    private static final String PROBE_USER_KEY = "PROBE_USER";

    public List<ProbeUser> queryProbeUser(User user) throws Exception {
        return redisTemplate.opsForList().range(PROBE_USER_KEY, 0, redisTemplate.opsForList().size(PROBE_USER_KEY));
    }

    public void setProbeUser(List<ProbeUser> probeUsers) throws Exception {
        redisTemplate.opsForList().leftPushAll(PROBE_USER_KEY, probeUsers);
/*        for (ProbeUser probeUser: probeUsers) {
            redisTemplate.opsForList().leftPush(PROBE_USER_KEY,probeUser);
        }*/

    }

}
