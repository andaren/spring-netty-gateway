package com.n3n4.im.imcore.handler.process;

import com.n3n4.im.imcore.config.SslContextFactory;
import com.n3n4.im.imcore.config.SslProperties;
import com.n3n4.im.imcore.gateway.constant.ChannelAttrKey;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * @Author: yeanzi
 * @Date: 18-7-3
 * @Time: 上午7:34
 * Email:  yeanzhi@ccclubs.com
 */
@Component
public class ChildChannelInitializer extends ChannelInitializer<SocketChannel> {
    public static final Logger LOG = LoggerFactory.getLogger(ChildChannelInitializer.class);

    /*shared channel handlers*/

    @Autowired
    private SslProperties sslProperties;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 如果需要配置传输层安全设置，则创建sslContext
        if (sslProperties.isOpen()) {
            SSLContext sslCtx = SslContextFactory.getServerContext(sslProperties);
            SSLEngine sslEngine = sslCtx.createSSLEngine();
            // 设置加密套件
            sslEngine.setUseClientMode(false);
            // 设置是否双向认证
            if (sslProperties.isAuthClient()) {
                sslEngine.setNeedClientAuth(true);
            }
            // ssl处理器(需放到首位)
            channel.pipeline().addFirst("SslEstablish", new SslHandler(sslEngine));
        }

        // 组装处理链路
        channel.pipeline()

                /*inbound*/
                // 空闲处理
                .addLast("idleHandler", new IdleStateHandler(360,0,0))
                .addLast("decoderHandler", null)
                .addLast("...", null)
                .addLast("exceptionHandler", null)

                /*outbound*/
                // 编码器
                .addLast("encodeHandler", null);

        /**
         * 初始化全链路共享的channelAttribute
         */
        ChannelAttrKey.initForAll(channel);
    }
}
