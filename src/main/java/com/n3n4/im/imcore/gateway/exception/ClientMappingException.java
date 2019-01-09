package com.n3n4.im.imcore.gateway.exception;

/**
 * @Author: yeanzi
 * @Date: 2018/7/17
 * @Time: 20:25
 * Email:  yeanzhi@ccclubs.com
 * 客户端映射异常
 */
public class ClientMappingException extends RuntimeException {

    public ClientMappingException() {
        super();
    }

    public ClientMappingException(String message) {
        super(message);
    }

    public ClientMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientMappingException(Throwable cause) {
        super(cause);
    }

    protected ClientMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
