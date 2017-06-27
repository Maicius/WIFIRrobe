package education.cs.scu.service.impl;

import education.cs.scu.entity.ProbeUser;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.ProbeUserMapper;
import education.cs.scu.service.ProbeUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/6/28.
 */
public class ProbeUserServiceImpl implements ProbeUserService{
    @Autowired
    private ProbeUserMapper probeUserMapper;
    public List<ProbeUser> queryProbeUser(User user) throws Exception {
        return probeUserMapper.queryProbeUser(user);
    }
}
