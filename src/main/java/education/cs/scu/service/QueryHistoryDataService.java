package education.cs.scu.service;

import education.cs.scu.entity.HistoryData;

import java.util.List;

/**
 * Created by maicius on 2017/6/29.
 */
public interface QueryHistoryDataService {
    List<HistoryData> queryActivityYear(HistoryData historyData) throws Exception;
    List<HistoryData> queryActivityMonth(HistoryData historyData) throws Exception;
    List<HistoryData> queryActivityDay(HistoryData historyData) throws Exception;
}
