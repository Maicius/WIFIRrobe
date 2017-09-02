package education.cs.scu.mapper.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.mapper.UserVisitMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.json.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    private static final String TABLE_USER_KEY = "table_user";
    private static final String TABLE_USER_VISIT_KEY = "table_user_visit";

    public void addUserVisit(UserVisitBean userVisitBean) throws Exception {
        redisTemplate.opsForList().leftPush(USER_VISIT_KEY, JSON.toJSONString(userVisitBean));
    }

    /**
     * 读取table_user_visit中的最新数据
     */

    public String queryUserVisit() throws Exception {
        redisTemplate.opsForHash().entries(TABLE_USER_VISIT_KEY);
        return null;
    }

    /**
     * 根据username 查询table_user 中的所有商店
     */
    public List<Object> queryUserShop(List<Integer> shopIdlist) throws Exception {

        List<Object> res = new ArrayList<Object>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(TABLE_USER_KEY);
        //System.out.println("queryUserShop");
        Set<Object> set = map.keySet();
        for (Integer id : shopIdlist) {
            for (Object key:set) {
                String keyValue = key.toString();
                //System.out.println(keyValue);
                if (id == Integer.parseInt(keyValue.split("||")[0])) {
                    res.add(JSON.parse((String) map.get(keyValue)));
                    //System.out.println(JSON.toJSONString( map.get(keyValue)));
                }
            }
        }
        return res;
    }
}
