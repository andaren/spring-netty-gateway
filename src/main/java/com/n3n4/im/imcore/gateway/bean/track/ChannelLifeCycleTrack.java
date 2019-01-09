package com.n3n4.im.imcore.gateway.bean.track;

import com.n3n4.im.imcore.gateway.constant.ChannelLiveStatus;

/**
 * @Author: yeanzi
 * @Date: 2018/7/4
 * @Time: 10:16
 * Email:  yeanzhi@ccclubs.com
 * 连接生命周期轨迹
 */
public class ChannelLifeCycleTrack {
    /**
     * 渠道对应的唯一标识
     */
    private String uniqueNo;

    /**
     * 流水号
     */
    private Short serialNo;

    /**
     * channel生命周期
     */
    private ChannelLiveStatus liveStatus;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 销毁时间
     */
    private Long destoryTime;

    public ChannelLiveStatus getLiveStatus() {
        return liveStatus;
    }

    public ChannelLifeCycleTrack setLiveStatus(ChannelLiveStatus liveStatus) {
        this.liveStatus = liveStatus;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public ChannelLifeCycleTrack setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getDestoryTime() {
        return destoryTime;
    }

    public ChannelLifeCycleTrack setDestoryTime(Long destoryTime) {
        this.destoryTime = destoryTime;
        return this;
    }

    public String getUniqueNo() {
        return uniqueNo;
    }

    public ChannelLifeCycleTrack setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
        return this;
    }

    public Short getSerialNo() {
        return serialNo;
    }

    public ChannelLifeCycleTrack setSerialNo(Short serialNo) {
        this.serialNo = serialNo;
        return this;
    }
}
