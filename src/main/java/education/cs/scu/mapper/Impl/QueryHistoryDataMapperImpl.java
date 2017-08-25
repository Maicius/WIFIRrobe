package education.cs.scu.mapper.Impl;

import education.cs.scu.entity.HistoryData;
import education.cs.scu.mapper.QueryHistoryDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * QueryHistoryDataMapperImpl
 *
 * @Author lch
 * @Create on 2017/08/25 14:57
 **/
public class QueryHistoryDataMapperImpl implements QueryHistoryDataMapper {

    @Autowired
    private RedisTemplate<String,HistoryData> redisTemplate;

    /**
     * 历史数据存入的时候，分别以historyData.getYear()，historyData.getMongth()，historyData.getDay()
     * 作为KEY
     * */

    public List<HistoryData> queryActivityYear(HistoryData historyData) throws Exception {

        return redisTemplate.opsForList().range(historyData.getYear(),0,redisTemplate.opsForList().size(historyData.getYear()));
    }

    public List<HistoryData> queryActivityMonth(HistoryData historyData) throws Exception {
        return redisTemplate.opsForList().range(historyData.getMongth(),0,redisTemplate.opsForList().size(historyData.getMongth()));
    }

    public List<HistoryData> queryActivityDay(HistoryData historyData) throws Exception {
        return redisTemplate.opsForList().range(historyData.getDay(),0,redisTemplate.opsForList().size(historyData.getDay()));
    }
}
