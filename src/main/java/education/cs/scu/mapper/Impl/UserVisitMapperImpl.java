package education.cs.scu.mapper.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import education.cs.scu.entity.UserBean;
import education.cs.scu.entity.UserFlow;
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
    private static final String TEST_KEY = "testlist";

    public void addUserVisit(UserVisitBean userVisitBean) throws Exception {
        redisTemplate.opsForList().leftPush(USER_VISIT_KEY, JSON.toJSONString(userVisitBean));
    }

    /**
     * 读取table_user_visit中的最新数据
     *
     * 利用redis list的trim删去已经读的数据
     */

    public List<UserVisitBean> queryUserVisit(List<Integer> shopIdlist) throws Exception {


        //在push数据的时候要采用rpush
        long size = redisTemplate.opsForList().size(TABLE_USER_VISIT_KEY);
        List<Object> tempList = redisTemplate.opsForList().range(TABLE_USER_VISIT_KEY, 0, size);
        List<UserVisitBean> resList = JSONArray.parseArray(tempList.toString(), UserVisitBean.class);
        redisTemplate.opsForList().trim(TABLE_USER_VISIT_KEY, size, -1);
        return resList;


/*        List<UserVisitBean> res = new ArrayList<UserVisitBean>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(TABLE_USER_VISIT_KEY);
        Set<Object> set = map.keySet();
        for (Integer id : shopIdlist) {
            for (Object key : set) {
                String keyValue = key.toString();
                if (id == Integer.parseInt(keyValue.split("||")[0])) {
                    res.add(JSON.parseObject((String) map.get(keyValue), UserVisitBean.class));
                }
            }
        }
        return res;*/
    }

    /**
     * 根据username 查询table_user 中的所有商店
     */
    public List<UserBean> queryUserShop(List<Integer> shopIdlist) throws Exception {

        List<UserBean> res = new ArrayList<UserBean>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(TABLE_USER_KEY);
        //System.out.println("queryUserShop");
        Set<Object> set = map.keySet();
        for (Integer id : shopIdlist) {
            for (Object key : set) {
                String keyValue;
                keyValue = key.toString();
                //System.out.println(keyValue);
                if (id == Integer.parseInt(keyValue.split("||")[0])) {
                    res.add(JSON.parseObject((String) map.get(keyValue), UserBean.class));
                    //System.out.println(JSON.toJSONString( map.get(keyValue)));
                }
            }
        }
        return res;
    }

}
