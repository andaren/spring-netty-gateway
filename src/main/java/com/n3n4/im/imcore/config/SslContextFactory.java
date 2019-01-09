package com.n3n4.im.imcore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Objects;

/**
 * @Author: yeanzi
 * @Date: 2018/8/27
 * @Time: 17:29
 * Email:  yeanzhi@ccclubs.com
 */
public class SslContextFactory {
    public static final Logger LOG = LoggerFactory.getLogger(SslContextFactory.class);

    private static final String PROTOCOL = "TLS";

    private static volatile SSLContext sslContextInstance = null;

    private SslContextFactory() {
    }

    /**
     * 初始化ssl context
     * @param sslProperties
     */
    private static void init(SslProperties sslProperties){
        Objects.requireNonNull(sslProperties);

        InputStream gatewayKeyStore = null;
        InputStream gatewayTrustStore = null;
        try {
            //初始化keyManagerFactory
            KeyStore ks = KeyStore.getInstance(sslProperties.getKeyStoreType());
            gatewayKeyStore = new FileInputStream(sslProperties.getFilePath());
            ks.load(gatewayKeyStore, sslProperties.getPassword().toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, sslProperties.getPassword().toCharArray());
            //初始化TrustManagerFacotry
            KeyStore ts = KeyStore.getInstance(sslProperties.getKeyStoreType());
            gatewayTrustStore = new FileInputStream(sslProperties.getFilePath());
            ts.load(gatewayTrustStore, sslProperties.getPassword().toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ts);
            //生成SSLContext
            sslContextInstance = SSLContext.getInstance(PROTOCOL);
            sslContextInstance.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        } catch (IOException e) {
            LOG.error("TLS 安全上下文创建出现IO异常， {}", e);
        } catch (Exception  e) {
            LOG.error("TLS 安全上下文创建出现异常， {}", e);
        } finally {
            if (null != gatewayKeyStore) {
                try {
                    gatewayKeyStore.close();
                } catch (IOException e) {
                    LOG.error("证书文件流gatewayKeyStore关闭失败， {}", e);
                }
            }
            if (null != gatewayTrustStore) {
                try {
                    gatewayTrustStore.close();
                } catch (IOException e) {
                    LOG.error("证书文件流gatewayTrustStore关闭失败， {}", e);
                }
            }
        }
    }

    /**
     * 获取已经创建好的单例Context
     * @param sslProperties     ssl配置信息(为空时，默认获取上次创建好的单例)
     * @return
     */
    public static SSLContext getServerContext(SslProperties sslProperties) {
        if(sslContextInstance == null){
            synchronized (SslContextFactory.class) {
                if (sslContextInstance == null) {
                    init(sslProperties);
                }
            }
        }
        return sslContextInstance;
    }
}

