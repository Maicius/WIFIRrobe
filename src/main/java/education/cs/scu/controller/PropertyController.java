package education.cs.scu.controller;

import education.cs.scu.entity.PropertyBean;
import education.cs.scu.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by maicius on 2017/6/28.
 */
@RestController
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/setProperty", method = RequestMethod.GET)
    public int setProperty(HttpServletRequest request,
                           @RequestParam("shop_id") Integer shop_id,
                           @RequestParam("mmac") String mmac,
                           @RequestParam("visitCycle") String visitCycle,
                           @RequestParam("visitRange") Double visitRange,
                           @RequestParam("visitRssi") Integer visitRssi,
                           @RequestParam("activityDegree") String activityDegree,
                           @RequestParam("visitTimeSplit") String visitTimeSplit) throws Exception {
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setShopId(shop_id);
        propertyBean.setMmac(mmac);
        propertyBean.setVisitCycle(visitCycle);
        propertyBean.setVisitRange(visitRange);
        propertyBean.setVisitRSSI(visitRssi);
        propertyBean.setActivityDegree(activityDegree);
        propertyBean.setVisitTimeSplit(visitTimeSplit);
        propertyBean.setPropertyType(false);

        int res = propertyService.setProperty(propertyBean);
        if (res > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/addProperty", method = RequestMethod.GET)
    public int addProperty(HttpServletRequest request,
                           @RequestParam("shop_id") Integer shop_id,
                           @RequestParam("mmac") String mmac,
                           @RequestParam("visitCycle") String visitCycle,
                           @RequestParam("visitRange") Double visitRange,
                           @RequestParam("visitRssi") Integer visitRssi,
                           @RequestParam("activityDegree") String activityDegree,
                           @RequestParam("visitTimeSplit") String visitTimeSplit) throws Exception {
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setShopId(shop_id);
        propertyBean.setMmac(mmac);
        propertyBean.setVisitCycle(visitCycle);
        propertyBean.setVisitRange(visitRange);
        propertyBean.setVisitRSSI(visitRssi);
        propertyBean.setActivityDegree(activityDegree);
        propertyBean.setVisitTimeSplit(visitTimeSplit);
        propertyBean.setPropertyType(false);
        int res = propertyService.addProperty(propertyBean);
        if (res > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "/queryProperty", method=RequestMethod.GET)
    public PropertyBean queryProperty(HttpServletRequest request,
                                      @RequestParam("shop_id") Integer shop_id,
                                      @RequestParam("mmac") String mmac) throws Exception{
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setShopId(shop_id);
        propertyBean.setMmac(mmac);

        return propertyService.queryProperty(propertyBean);
    }
}
