package education.cs.scu.service.impl;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import education.cs.scu.Util.VerifyCodeUtil;
import education.cs.scu.entity.User;
import education.cs.scu.mapper.UserMapper;
import education.cs.scu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.sampled.BooleanControl;
import java.util.Date;


/**
 * Created by maicius on 2017/3/31.
 */
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;

    public User doUserLogin(User user) throws Exception{
        return userMapper.doUserLogin(user);
    }
    public boolean verifyCode(User user) throws Exception {
        String url = "http://gw.api.taobao.com/router/rest";
        int  code = VerifyCodeUtil.createVerifyCode();
        TaobaoClient client = new DefaultTaobaoClient(url,
                "23780335",
                        "e158afdc661f0d72cf0855b05900f774");

        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("1");
        //必须填写normal
        req.setSmsType("normal");
        //应用名称
        req.setSmsFreeSignName("WIFI探针管理平台");
        //电话号码
        req.setRecNum(user.getUserName());
        //模板
        req.setExtend(user.getUserName());
        req.setSmsFreeSignName("SCUNET");

        req.setSmsParamString("{\"name\":"+user.getUserName() + ", \"code\":" + String.valueOf(code)+"}");
        req.setSmsTemplateCode("SMS_74350014");
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            // 这里是我设置的一个保存验证码 机制。按照实际需求，自行设计
            user.setVerifyCode(String.valueOf(code));
            user.setVerifyTime(String.valueOf(new Date()));
            System.out.println(rsp.getBody());
            return true;
        } catch (ApiException e) {
             e.printStackTrace();
             return false;
        }
    }
}
