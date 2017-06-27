package education.cs.scu;

import education.cs.scu.entity.UserVisitTimeBean;
import education.cs.scu.service.UserVisitTimeService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Wang Han on 2017/6/27 15:47.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 *
 * @author Wang Han
 */
public class testExcel {
    @Resource
    private UserVisitTimeService userVisitTimeService;

    @Test
    public void test1() {
        List<UserVisitTimeBean> userVisitTimeBeanArrayList = userVisitTimeService.getUserVisitTime(0, 2);
        for (UserVisitTimeBean item : userVisitTimeBeanArrayList) {

            System.out.println(item.getVisitTime());
        }

    }
}
