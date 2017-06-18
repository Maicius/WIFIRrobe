package education.cs.scu.service;

import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;

/**
 * Created by Wang Han on 2017/6/18 15:18.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 */
public interface UserVisitDao {
    void addUserVisit(UserVisitBean userFlow) throws Exception;
    UserFlow queryUserVisit() throws Exception;
}
