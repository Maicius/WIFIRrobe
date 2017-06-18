package education.cs.scu.mapper;

import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;

/**
 * Created by maicius on 2017/6/18.
 */
public interface UserVisitMapper {
    void addUserVisit(UserVisitBean userVisitBean) throws Exception;
    UserFlow queryUserVisit() throws Exception;
}
