package education.cs.scu.controller;

import com.alibaba.fastjson.JSON;
import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.service.ShopService;
import education.cs.scu.service.UserVisitService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lch
 * @Create on 2017/09/02 20:40
 **/
@RestController
public class UserVisitController {
    @Autowired
    private UserVisitService userVisitService;

    @Autowired
    private ShopService shopService;

    /**
     * 根据userName 查询其名下的商店
     *
     * 查询redis中的table_user表
     * */

    @RequestMapping(value = "queryUserShop", method = RequestMethod.GET)
    public List<Object> queryUserShop(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam("userName") String userName) throws Exception {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShop_owner(userName);

        List<Integer> shopIdlist = new ArrayList<Integer>();
        List<ShopInfo> shopInfoList = new ArrayList<ShopInfo>();
        shopInfoList = shopService.queryShopInfos(shopInfo);
        for (ShopInfo si : shopInfoList) {
            shopIdlist.add(si.getShop_id());
            System.out.println(si.getShop_id());
        }
        String res = JSON.toJSONString(userVisitService.queryUserShop(shopIdlist),true);
        System.out.println(res);
        return userVisitService.queryUserShop(shopIdlist);
    }

}
