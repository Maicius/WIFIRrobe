package education.cs.scu.entity;

import java.io.Serializable;

/**
 * Created by Wang Han on 2017/6/20 16:41.
 * E-mail address is wanghan0501@vip.qq.com.
 * Copyright © 2017 Wang Han. SCU. All Rights Reserved.
 */
public class UserVisitTimeBean implements Serializable {
    private static final long serialVersinUID = 351877796426921776L;

    // 自增长ID
    private int autoId;
    // 商店ID
    private int shopId;
    // Mac地址
    private String mac;
    // 访问时间
    private String visitTime;

    public static long getSerialVersinUID() {
        return serialVersinUID;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
