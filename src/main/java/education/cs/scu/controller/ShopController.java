package education.cs.scu.controller;

import education.cs.scu.entity.ProbeInfo;
import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.User;
import education.cs.scu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maicius on 2017/6/27.
 */
@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * 查询商场信息
     *
     * @param request
     * @param userName
     * @return 商场列表
     * @throws Exception
     */
    @RequestMapping(value = "/queryShopInfos", method = RequestMethod.GET)
    public List<ShopInfo> queryShopInfos(HttpServletRequest request,
                                         @RequestParam("userName") String userName) throws Exception {

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_owner(userName);
        List<ShopInfo> shopInfos = new ArrayList<ShopInfo>();
        shopInfos.add(shopInfo);

        List<ShopInfo> resShopInfos = shopService.queryShopInfos(shopInfos);

        if (resShopInfos.size() > 0) {
            System.out.println("查询成功");
        }
        return resShopInfos;
    }

    /**
     * 增加商场信息
     *
     * @param request
     * @param userName
     * @param shopName
     * @param shopAddr
     * @param shopManager
     * @param shopTelephone
     * @param shopDescribe
     * @return 成功返回"success",失败返回null
     * @throws Exception
     */
    @RequestMapping(value = "addShopInfo", method = RequestMethod.GET)
    public String addShoInfo(HttpServletRequest request,
                             @RequestParam("userName") String userName,
                             @RequestParam("shopName") String shopName,
                             @RequestParam("shopAddr") String shopAddr,
                             @RequestParam("shopManager") String shopManager,
                             @RequestParam("shopTelephone") String shopTelephone,
                             @RequestParam("shopDescribe") String shopDescribe) throws Exception {

        //localhost:8080/addShopInfo.action?userName=WW&shopName=110110&shopAddr=test&shopManager&shopTelephone=110110&shopDescribe=test
        long shopID = shopService.getUniqueShopId();
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_id(shopID);
        shopInfo.setShop_name(shopName);
        shopInfo.setShop_addr(shopAddr);
        shopInfo.setShop_telephone(shopTelephone);
        shopInfo.setShop_manager(shopManager);
        shopInfo.setShop_describe(shopDescribe);
        shopInfo.setShop_owner(userName);
        int success = shopService.addShopInfo(shopInfo);
        System.out.println(success);
        if (success > 0) {
            return "success";
        } else {
            return "";
        }
    }


    /**
     * 更新商场信息
     *
     * @param request
     * @param userName
     * @param shopID
     * @param shopName
     * @param shopAddr
     * @param shopManager
     * @param shopTelephone
     * @param shopDescribe
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateShopInfo", method = RequestMethod.GET)
    public String addShopInfo(HttpServletRequest request,
                              @RequestParam("userName") String userName,
                              @RequestParam("shopID") Integer shopID,
                              @RequestParam("shopName") String shopName,
                              @RequestParam("shopAddr") String shopAddr,
                              @RequestParam("shopManager") String shopManager,
                              @RequestParam("shopTelephone") String shopTelephone,
                              @RequestParam("shopDescribe") String shopDescribe) throws Exception {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_id(shopID);
        shopInfo.setShop_name(shopName);
        shopInfo.setShop_addr(shopAddr);
        shopInfo.setShop_telephone(shopTelephone);
        shopInfo.setShop_manager(shopManager);
        shopInfo.setShop_describe(shopDescribe);
        shopInfo.setShop_owner(userName);
        int success = shopService.updateShopInfo(shopInfo);
        System.err.println(success);
        if (success > 0) {
            return "success";
        } else {
            return "";
        }
    }

    /**
     * 根据用户名查询探针信息
     *
     * @param request
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryProbeInfos", method = RequestMethod.GET)
    public List<ProbeInfo> queryProbeInfos(HttpServletRequest request,
                                           @RequestParam("userName") String userName,
                                           @RequestParam("shopId") int shopId) throws Exception {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_id(shopId);
        shopInfo.setShop_owner(userName);

        return shopService.queryProbeInfos(shopInfo);
    }

    /**
     * 根据用户名和商场ID查询探针
     *
     * @param request
     * @param userName
     * @param shop_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryShopProbeInfos", method = RequestMethod.GET)
    public List<ProbeInfo> queryShopProbeInfos(HttpServletRequest request,
                                               @RequestParam("userName") String userName,
                                               @RequestParam("shop_id") int shop_id) throws Exception {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_owner(userName);
        shopInfo.setShop_id(shop_id);
        return shopService.queryshopProbeInfos(shopInfo);
    }
}
