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

    public List<ShopInfo> queryShopInfos(List<ShopInfo> shopInfos) {

        List<ShopInfo> results = new ArrayList<ShopInfo>();
        for (ShopInfo si : shopInfos) {
            results.add((ShopInfo) redisTemplate.opsForHash().get(SHOP_INFO_KEY, si.getShop_id()));
        }
        return results;
        //long size = redisTemplate.opsForList().size(SHOP_INFO_KEY + shopInfo.getShop_id());
        //return redisTemplate.opsForList().range(SHOP_INFO_KEY + shopInfo.getShop_id(), 0, size);
    }

    public int addShopInfo(ShopInfo shopInfo) {
        try {
            redisTemplate.opsForHash().put(SHOP_INFO_KEY, shopInfo.getShop_id(), shopInfo);
            //redisTemplate.opsForList().leftPush(SHOP_INFO_KEY + shopInfo.getShop_id(), shopInfo);
        } catch (RedisConnectionFailureException e) {
            return 0;
        }
        return 1;
    }

    public int updateShopInfo(ShopInfo shopInfo) {
        try {
            redisTemplate.opsForHash().put(SHOP_INFO_KEY, shopInfo.getShop_id(), shopInfo);
            //long index = 0;
            //redisTemplate.opsForList().set(SHOP_INFO_KEY + shopInfo.getShop_id(), index, shopInfo);
        } catch (RedisConnectionFailureException e) {
            return 0;
        }
        return 1;
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
