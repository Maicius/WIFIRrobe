package education.cs.scu.mapper;

import education.cs.scu.entity.User;
import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.javautils.ExcelUtil;

import java.util.List;

/**
 * Created by maicius on 2017/6/18.
 */
public interface UserVisitMapper {
    void addUserVisit(UserVisitBean userVisitBean) throws Exception;
    String queryUserVisit() throws Exception;
    List<Object> queryUserShop(List<Integer> shopIdlist) throws Exception;
}
