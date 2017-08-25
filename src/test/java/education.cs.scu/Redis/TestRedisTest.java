package education.cs.scu.Redis;

import education.cs.scu.BaseResources;
import education.cs.scu.entity.ProbeUser;
import education.cs.scu.service.ProbeUserService;
import education.cs.scu.tool.RedisTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maicius on 2017/7/24.
 */
public class TestRedisTest extends BaseResources{

    @Resource(name = "testRedis")
    RedisTest redisTest;

    List<ProbeUser> probeUsers = new ArrayList<ProbeUser>();

    @Test
    public void testProbeUser(){

        ProbeUser probeUser = new ProbeUser();
        probeUser.setMac("aa.bb.cc.dd.ee.ff");
        probeUser.setAddr("四川省成都市双流区四川大学江安校区西园7舍");
        probeUser.setMmac("10000");
        probeUser.setBrand("brand_01");
        probeUser.setActivity_degree("30");

        ProbeUser probeUser1 = new ProbeUser();
        probeUser1.setMac("FF.FF.FF.FF.FF.FF");
        probeUser1.setAddr("四川省成都市双流区四川大学江安校区西园8舍");
        probeUser1.setMmac("11111");
        probeUser1.setBrand("brand_02");
        probeUser1.setActivity_degree("40");

        for (int i = 0;i<10;i++) {
            if (i/2 == 0) {
                probeUsers.add(probeUser);
            }else {
                probeUsers.add(probeUser1);
            }

        }

        try {
            redisTest.setProbeUser(probeUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
