package com.n3n4.im.imcore.gateway.bean.attr;

import com.n3n4.im.imcore.gateway.inf.ChannelAttr;

/**
 * describe: 渠道健康数据
 * author  : Andaren
 * email   : 603822061@qq.com
 * date    : 2018/8/9
 * time    : 下午11:17
 * version : v1.0
 * update_log:
 * date[更新日期] time[更新时间] updater[更新人]     for[原因]
 * 2018-9-14        15.28       Andaren         增加最近一次报文的时间和内容
 *
 **/
public abstract class AbstractChannelHealthyAttr extends AbstractChannelAttr implements ChannelAttr {
    /**
     * 当前客户端IP端口信息
     */
    private String currentClientAddress;

    /**
     * 当前服务器IP端口信息
     */
    private String currentServerAddress;

    /**
     * 最近一次报文时间
     */
    private String lastPackageTime;

    /**
     * 最近一次报文的描述
     */
    private String lastPackageDes;

    /**
     * 最近一次报文的原始报文
     */
    private String lastPackageHex;

    public String getCurrentClientAddress() {
        return currentClientAddress;
    }

    public AbstractChannelHealthyAttr setCurrentClientAddress(String currentClientAddress) {
        this.currentClientAddress = currentClientAddress;
        return this;
    }

    public String getCurrentServerAddress() {
        return currentServerAddress;
    }

    public AbstractChannelHealthyAttr setCurrentServerAddress(String currentServerAddress) {
        this.currentServerAddress = currentServerAddress;
        return this;
    }

    public String getLastPackageTime() {
        return lastPackageTime;
    }

    public AbstractChannelHealthyAttr setLastPackageTime(String lastPackageTime) {
        this.lastPackageTime = lastPackageTime;
        return this;
    }

    public String getLastPackageHex() {
        return lastPackageHex;
    }

    public AbstractChannelHealthyAttr setLastPackageHex(String lastPackageHex) {
        this.lastPackageHex = lastPackageHex;
        return this;
    }

    public String getLastPackageDes() {
        return lastPackageDes;
    }

    public AbstractChannelHealthyAttr setLastPackageDes(String lastPackageDes) {
        this.lastPackageDes = lastPackageDes;
        return this;
    }
}
