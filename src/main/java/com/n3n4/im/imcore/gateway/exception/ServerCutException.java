package com.n3n4.im.imcore.gateway.exception;

/**
 * @Author: yeanzi
 * @Date: 2018/9/10
 * @Time: 11:08
 * Email:  yeanzhi@ccclubs.com
 * 需要服务端断开客户端连接的异常
 *      需要服务端主动断开的场景：
 *          - 认证失败
 *          - 服务端到了无法继续处理的过程，只好断开客户端连接
 *
 */
public class ServerCutException extends RuntimeException {

    public ServerCutException() {
        super();
    }

    public ServerCutException(String message) {
        super(message);
    }

    public ServerCutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerCutException(Throwable cause) {
        super(cause);
    }

    protected ServerCutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
