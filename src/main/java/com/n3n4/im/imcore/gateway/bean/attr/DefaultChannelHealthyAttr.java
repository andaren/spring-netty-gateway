package com.n3n4.im.imcore.gateway.bean.attr;

/**
 * describe: 
 * author  : Andaren
 * email   : 603822061@qq.com
 * date    : 2018/8/9
 * time    : 下午10:58
 * version : v1.0
 * update_log: 
 * date[更新日期] time[更新时间] updater[更新人]     for[原因]
 * 
 **/
public class DefaultChannelHealthyAttr extends AbstractChannelHealthyAttr {
    /**
     * 收到的包的数量
     */
    private int packageNum;

    /**
     * 定位包数量
     */
    private int positionPackageNum;

    /**
     * 错误包数
     */
    private int errorPackageNum;

    public int getPackageNum() {
        return packageNum;
    }

    public DefaultChannelHealthyAttr setPackageNum(int packageNum) {
        this.packageNum = packageNum;
        return this;
    }

    public int getPositionPackageNum() {
        return positionPackageNum;
    }

    public DefaultChannelHealthyAttr setPositionPackageNum(int positionPackageNum) {
        this.positionPackageNum = positionPackageNum;
        return this;
    }

    public int getErrorPackageNum() {
        return errorPackageNum;
    }

    public DefaultChannelHealthyAttr setErrorPackageNum(int errorPackageNum) {
        this.errorPackageNum = errorPackageNum;
        return this;
    }
}
