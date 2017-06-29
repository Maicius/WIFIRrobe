package education.cs.scu.service.impl;

import education.cs.scu.entity.HistoryData;
import education.cs.scu.mapper.QueryHistoryDataMapper;
import education.cs.scu.service.QueryHistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/6/28.
 */
public class QueryHistoryDataServiceImpl implements QueryHistoryDataService{
    @Autowired
    QueryHistoryDataMapper queryHistoryData;

    public List<HistoryData> queryActivityYear(HistoryData historyData) throws Exception {
        return queryHistoryData.queryActivityYear(historyData);
    }

    public List<HistoryData> queryActivityMonth(HistoryData historyData) throws Exception {
        return queryHistoryData.queryActivityMonth(historyData);
    }

    public List<HistoryData> queryActivityDay(HistoryData historyData) throws Exception {
        return queryHistoryData.queryActivityDay(historyData);
    }
}
