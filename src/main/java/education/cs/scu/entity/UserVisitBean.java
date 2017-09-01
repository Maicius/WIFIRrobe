package education.cs.scu.entity;

import java.io.Serializable;

/**
 * 用户访问类
 * <p>
 * Created by Wang Han on 2017/6/18 15:11.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 */
public class UserVisitBean implements Serializable{

    private static final long serialVersionUID = 1647357927092562559L;

    private Long shopId;
    private String mac;
    private Long time;
    private Integer totalFlow;
    private Integer checkInFlow;
    private Double checkInRate;
    private Double shallowVisitRate;
    private Double deepVisitRate;

    public UserVisitBean() {

    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Integer totalFlow) {
        this.totalFlow = totalFlow;
    }

    public Integer getCheckInFlow() {
        return checkInFlow;
    }

    public void setCheckInFlow(Integer checkInFlow) {
        this.checkInFlow = checkInFlow;
    }

    public Double getCheckInRate() {
        return checkInRate;
    }

    public void setCheckInRate(Double checkInRate) {
        this.checkInRate = checkInRate;
    }

    public Double getShallowVisitRate() {
        return shallowVisitRate;
    }

    public void setShallowVisitRate(Double shallowVisitRate) {
        this.shallowVisitRate = shallowVisitRate;
    }

    public Double getDeepVisitRate() {
        return deepVisitRate;
    }

    public void setDeepVisitRate(Double deepVisitRate) {
        this.deepVisitRate = deepVisitRate;
    }
}
