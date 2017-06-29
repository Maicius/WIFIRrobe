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

    @RequestMapping(value = "/queryActivityMonth", method = RequestMethod.GET)
    public List<HistoryData> queryActivityMonth(HttpServletRequest request,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("activityMonth") String activityMonth) throws Exception{
        HistoryData historyData = new HistoryData();
        historyData.setYear(activityMonth);
        historyData.setUserName(userName);
        return queryHistoryDataService.queryActivityMonth(historyData);
    }

    @RequestMapping(value = "/queryActivityYear", method = RequestMethod.GET)
    public List<HistoryData> queryActivityYear(HttpServletRequest request,
                                               @RequestParam("userName") String userName,
                                               @RequestParam("activityYear") String activityYear) throws Exception{
        HistoryData historyData = new HistoryData();
        historyData.setYear(activityYear);
        historyData.setUserName(userName);

        return queryHistoryDataService.queryActivityYear(historyData);
    }

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
