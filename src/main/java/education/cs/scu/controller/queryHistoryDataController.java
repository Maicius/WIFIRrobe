package education.cs.scu.controller;

import education.cs.scu.entity.HistoryData;
import education.cs.scu.service.QueryHistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by maicius on 2017/6/28.
 */
@CrossOrigin
@RestController
public class queryHistoryDataController {
    @Autowired
    private QueryHistoryDataService queryHistoryDataService;

    /**
     * 根据月度查询活跃数据
     * @param request
     * @param userName
     * @param activityMonth
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryActivityMonth", method = RequestMethod.GET)
    public List<HistoryData> queryActivityMonth(HttpServletRequest request,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("activityMonth") String activityMonth) throws Exception{
        HistoryData historyData = new HistoryData();
        historyData.setYear(activityMonth);
        historyData.setUserName(userName);
        return queryHistoryDataService.queryActivityMonth(historyData);
    }

    /**
     * 根据年度查询活跃信息
     * @param request
     * @param userName
     * @param activityYear
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryActivityYear", method = RequestMethod.GET)
    public List<HistoryData> queryActivityYear(HttpServletRequest request,
                                               @RequestParam("userName") String userName,
                                               @RequestParam("activityYear") String activityYear) throws Exception{
        HistoryData historyData = new HistoryData();
        historyData.setYear(activityYear);
        historyData.setUserName(userName);

        return queryHistoryDataService.queryActivityYear(historyData);
    }

    /**
     * 根据日期查询一天中每个小时的活跃信息
     * @param request
     * @param userName
     * @param activityDay
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryActivityDay", method = RequestMethod.GET)
    public List<HistoryData> queryActivityDay(HttpServletRequest request,
                                              @RequestParam("userName") String userName,
                                              @RequestParam("activityDay") String activityDay) throws Exception{
        HistoryData historyData = new HistoryData();
        historyData.setUserName(userName);
        historyData.setDay(activityDay);

        return queryHistoryDataService.queryActivityDay(historyData);
    }

}
