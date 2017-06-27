package education.cs.scu.service;

/**
 * Created by maicius on 2017/6/27.
 */

import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.User;
import java.util.List;

public interface ShopService {
    List<ShopInfo> queryShopInfos(User user);
    int addShopInfo(ShopInfo shopInfo);
}
