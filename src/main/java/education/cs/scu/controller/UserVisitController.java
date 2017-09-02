package education.cs.scu.controller;

import com.alibaba.fastjson.JSON;
import education.cs.scu.component.QueryUsersShopInfo;
import education.cs.scu.entity.ShopInfo;
import education.cs.scu.entity.UserBean;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.service.ShopService;
import education.cs.scu.service.UserVisitService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.cs.US_ASCII;

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

    @Autowired
    QueryUsersShopInfo queryUsersShopInfo;

    /**
     * 根据userName 查询其名下的商店
     * <p>
     * 查询redis中的table_user表
     */

    @RequestMapping(value = "queryUserShop", method = RequestMethod.GET)
    public List<UserBean> queryUserShop(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam("userName") String userName) throws Exception {
        List<Integer> shopIdlist = queryUsersShopInfo.getShopId(userName);
        return userVisitService.queryUserShop(shopIdlist);
    }


    /**
     * 查询redis中的table_user_visit表
     * 通过webSocket 推送到 前端
     */

    @RequestMapping(value = "queryUserVisit", method = RequestMethod.GET)
    public List<UserVisitBean> queryUserVisit(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam("userName") String userName) throws Exception {

        List<Integer> shopIdlist = queryUsersShopInfo.getShopId(userName);
        return userVisitService.queryUserVisit(shopIdlist);
    }

}
