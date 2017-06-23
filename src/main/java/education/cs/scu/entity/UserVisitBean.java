package education.cs.scu.entity;

/**
 * 用户访问类
 * <p>
 * Created by Wang Han on 2017/6/18 15:11.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 */
public class UserVisitBean {
    private long shopId;
    private String mac;
    private String time;
    private int totalFlow;
    private int checkInFlow;
    private double checkInRate;
    private double shallowVisitRate;
    private double deepVisitRate;

    public UserVisitBean() {

    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(int totalFlow) {
        this.totalFlow = totalFlow;
    }

    public int getCheckInFlow() {
        return checkInFlow;
    }

    public void setCheckInFlow(int checkInFlow) {
        this.checkInFlow = checkInFlow;
    }

    public double getCheckInRate() {
        return checkInRate;
    }

    public void setCheckInRate(double checkInRate) {
        this.checkInRate = checkInRate;
    }

    public double getShallowVisitRate() {
        return shallowVisitRate;
    }

    public void setShallowVisitRate(double shallowVisitRate) {
        this.shallowVisitRate = shallowVisitRate;
    }

    public double getDeepVisitRate() {
        return deepVisitRate;
    }

    public void setDeepVisitRate(double deepVisitRate) {
        this.deepVisitRate = deepVisitRate;
    }

}
