package com.n3n4.im.imcore.gateway.exception;

/**
 * @Author: yeanzi
 * @Date: 2018/4/27
 * @Time: 10:51
 * Email:  yeanzhi@ccclubs.com
 * 渠道未连接异常
 */
public class ChannelDisconnException extends RuntimeException {

    public ChannelDisconnException() {
        super("处理消息时发现渠道未连接");
    }

    public ChannelDisconnException(String vin) {
        super("处理消息时发现渠道([vin=" + vin + "])未连接.");
    }

    public ChannelDisconnException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelDisconnException(Throwable cause) {
        super(cause);
    }

    protected ChannelDisconnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
