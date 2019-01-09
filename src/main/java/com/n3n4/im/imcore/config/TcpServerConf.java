package com.n3n4.im.imcore.config;

import com.n3n4.im.imcore.handler.process.ChildChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @Author: yeanzi
 * @Date: 18-7-3
 * @Time: 上午7:38
 * Email:  yeanzhi@ccclubs.com
 */
@Component
public class TcpServerConf {
    private static final Logger LOG = Logger.getLogger("TcpServer");

    @Autowired
    private NettyProperties nettyProperties;

    @Autowired
    private ChildChannelInitializer childChannelInitializer;

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroup() {
        int bossCount = nettyProperties.getBossCount();
        if (nettyProperties.isUseLinuxEpoll() && Epoll.isAvailable()) {
            return new EpollEventLoopGroup(bossCount);
        } else {
            return new NioEventLoopGroup(bossCount);
        }
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroup() {
        int workerCount = nettyProperties.getWorkerCount();
        if (nettyProperties.isUseLinuxEpoll() && Epoll.isAvailable()) {
            return new EpollEventLoopGroup(workerCount);
        } else {
            return new NioEventLoopGroup(workerCount);
        }
    }

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup());
        if (nettyProperties.isUseLinuxEpoll() && Epoll.isAvailable()) {
            b.channel(EpollServerSocketChannel.class);
        } else {
            b.channel(NioServerSocketChannel.class);
        }
        b.handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(childChannelInitializer);

        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        // channel参数设置
        for (ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }

        if (nettyProperties.isCheckBufferLack()) {
            LOG.warning("缓存检查: 开启");
            ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
        }
        return b;
    }

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<>();
        options.put(ChannelOption.SO_KEEPALIVE, nettyProperties.isKeepAlive());
        options.put(ChannelOption.SO_BACKLOG, nettyProperties.getBacklog());
        return options;
    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPort() {
        int tcpPort = nettyProperties.getTcpPort();
        tcpPort = tcpPort==0?9999:tcpPort;
        return new InetSocketAddress(tcpPort);
    }
}
