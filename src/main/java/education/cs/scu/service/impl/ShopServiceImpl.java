package education.cs.scu.service.impl;

import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.ShopMapper;
import education.cs.scu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/6/27.
 */
public class ShopServiceImpl implements  ShopService{

    @Autowired
    ShopMapper shopMapper;
    public List<ShopInfo> queryShopInfos(User user) {
        return shopMapper.queryShopInfos(user);
    }

    public int addShopInfo(ShopInfo shopInfo){
        return shopMapper.addShopInfo(shopInfo);
    }
}
