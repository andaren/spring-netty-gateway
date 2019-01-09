package com.n3n4.im.imcore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yeanzi
 * @Date: 2018/8/28
 * @Time: 10:19
 * Email:  yeanzhi@ccclubs.com
 * 网关socket安全配置信息
 */
@ConfigurationProperties(prefix = "gateway.ssl")
public class SslProperties {
    /**
     * 是否开启ssl认证加密处理
     */
    private boolean open;

    /**
     * 是否双向认证
     * true: 服务端需要认证客户端证书
     * false: 单向认证即可
     */
    private boolean authClient;

    /**
     * 服务端证书库类型
     */
    private String keyStoreType;

    /**
     * 服务端证书文件路径
     */
    private String filePath;

    /**
     * 服务端证书对应的密钥
     */
    private String password;

    public boolean isOpen() {
        return open;
    }

    public SslProperties setOpen(boolean open) {
        this.open = open;
        return this;
    }

    public boolean isAuthClient() {
        return authClient;
    }

    public SslProperties setAuthClient(boolean authClient) {
        this.authClient = authClient;
        return this;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public SslProperties setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public SslProperties setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SslProperties setPassword(String password) {
        this.password = password;
        return this;
    }
}
