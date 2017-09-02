package education.cs.scu.service.impl;

import education.cs.scu.entity.User;
import education.cs.scu.entity.UserFlow;
import education.cs.scu.entity.UserVisitBean;
import education.cs.scu.mapper.UserVisitMapper;
import education.cs.scu.service.UserVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Wang Han on 2017/6/18 15:19.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 */

@Service("userVisitService")
public class UserVisitServiceImpl implements UserVisitService {

    @Autowired
    UserVisitMapper userVisitMapper;

    public void addUserVisit(UserVisitBean userFlow) throws Exception {
        userVisitMapper.addUserVisit(userFlow);
    }

    public List<UserVisitBean> queryUserVisit(List<Integer> shopIdlist) throws Exception {
        return userVisitMapper.queryUserVisit(shopIdlist);
    }

    public List<Object> queryUserShop(List<Integer> shopIdlist) throws Exception {
        return userVisitMapper.queryUserShop(shopIdlist);
    }

}
