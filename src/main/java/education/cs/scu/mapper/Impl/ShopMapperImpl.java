package education.cs.scu.mapper.Impl;

import education.cs.scu.entity.ProbeInfo;
import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ShopMapperImpl
 *
 * @Author lch
 * @Create on 2017/08/25 15:09
 **/
public class ShopMapperImpl implements ShopMapper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String SHOP_INFO_KEY = "SHOP_INFO";
    private static final String PROBE_INFO_KEY = "PROBE_INFO";
    private static final String UNIQUE_SHOP_ID_KEY  = "UNIQUE_SHOP_ID";

    public List<ShopInfo> queryShopInfos(List<ShopInfo> shopInfos) {

        List<ShopInfo> results = new ArrayList<ShopInfo>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(SHOP_INFO_KEY);
        for (ShopInfo si : shopInfos) {

            if (map.containsKey(si.getShop_owner())) {
                results.add((ShopInfo) map.get(si.getShop_owner()));
                System.out.println(map.get(si.getShop_owner()));
            }
            //results.add((ShopInfo) redisTemplate.opsForHash().get(SHOP_INFO_KEY, si.getShop_owner()));
        }


        return results;
    }

    /**
     * 以username作为唯一key，username是用户注册时候的手机号，
     * */
    public int addShopInfo(ShopInfo shopInfo) {
        try {
            redisTemplate.opsForHash().put(SHOP_INFO_KEY, shopInfo.getShop_owner(), shopInfo);
        } catch (RedisConnectionFailureException e) {
            return 0;
        }
        return 1;
    }

    public int updateShopInfo(ShopInfo shopInfo) {
        try {
            redisTemplate.opsForHash().put(SHOP_INFO_KEY, shopInfo.getShop_owner(), shopInfo);
            //long index = 0;
            //redisTemplate.opsForList().set(SHOP_INFO_KEY + shopInfo.getShop_id(), index, shopInfo);
        } catch (RedisConnectionFailureException e) {
            return 0;
        }
        return 1;
    }

    /**
     * 用于生成唯一的SHOPid**/
    public long getUniqueShopId() {
        long res = 0;
        res = System.currentTimeMillis();
        boolean b = redisTemplate.opsForSet().isMember(UNIQUE_SHOP_ID_KEY,String.valueOf(res));
        if (b)  {
            res = getUniqueShopId();
        }
        redisTemplate.opsForSet().add(UNIQUE_SHOP_ID_KEY,String.valueOf(res));
        return res;

    }

    public List<ProbeInfo> queryProbeInfos(ShopInfo shopInfo) {
        List<ProbeInfo> results = new ArrayList<ProbeInfo>();
        results.add((ProbeInfo) redisTemplate.opsForHash().get(PROBE_INFO_KEY, shopInfo.getShop_id()));
        return results;
    }

    public List<ProbeInfo> queryShopProbeInfo(ShopInfo shopInfo) {
        List<ProbeInfo> results = new ArrayList<ProbeInfo>();
        results.add((ProbeInfo) redisTemplate.opsForHash().get(PROBE_INFO_KEY, shopInfo.getShop_id()));
        return results;
    }
}
