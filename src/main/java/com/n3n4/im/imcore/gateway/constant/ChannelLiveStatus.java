package com.n3n4.im.imcore.gateway.constant;

/**
 * @Author: yeanzi
 * @Date: 2018/7/4
 * @Time: 10:18
 * Email:  yeanzhi@ccclubs.com
 * channel当前状态
 */
public enum ChannelLiveStatus {

    /**
     * channel创建
     */
    ONLINE_CREATE(0),

    /**
     * 客户端建立连接
     */
    ONLINE_CONNECT(1),

    /**
     * 客户端已注册
     */
    ONLINE_REGISTER(2),

    /**
     * 客户端已经鉴权
     */
    ONLINE_AUTH(3),

    // 后面的状态都可以获取uniqueNo
    // -----------------------------------------------

    /**
     * 连接由于读空闲断开
     */
    OFFLINE_IDLE(4),

    /**
     * 连接被平台主动断开
     */
    OFFLINE_SERVER_CUT(5),

    /**
     * 客户端主动断开了连接
     */
    OFFLINE_CLIENT_CUT(6),

    /**
     * 客户端登出
     */
    OFFLINE_LOGOUT(7),

    /**
     * 下线完成
     */
    OFFLINE_END(8);

    private int code;
    ChannelLiveStatus (int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
