package com.n3n4.im.imcore.gateway.exception;

/**
 * @Author: yeanzi
 * @Date: 2018/4/27
 * @Time: 21:29
 * Email:  yeanzhi@ccclubs.com
 * 当处理终端下线时，出现异常，此时，已不能通过正常的终端下线断开连接，必须强制断开该连接
 */
public class OfflineException extends RuntimeException {

    public OfflineException(String msg) {
        super(msg);
    }

    public OfflineException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
