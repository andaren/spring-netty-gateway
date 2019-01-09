package com.n3n4.im.imcore.gateway.util;

import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @Author: yeanzi
 * @Date: 2018/4/27
 * @Time: 10:43
 * Email:  yeanzhi@ccclubs.com
 * 通道工具类
 */
public final class ChannelUtils {
    public static final Logger LOG = LoggerFactory.getLogger(ChannelUtils.class);
    private ChannelUtils(){}

    /**
     * 校验channel是否存在
     * @param channel
     * @return
     */
    public static boolean notConnected(SocketChannel channel) {
        Objects.requireNonNull(channel);
        boolean closed = Objects.isNull(channel) ||
                !channel.isOpen();

        // 因为可能存在其他判定条件，所以这里不用最简便的写法
        return closed;
    }

    /**
     * 获取本地服务器的ip地址
     * @param channel
     * @return
     */
    public static String getLocalAddress(SocketChannel channel) {
        Objects.requireNonNull(channel);

        return channel.localAddress().getAddress().getHostAddress();
    }

    /**
     * 获取客户端ip地址
     * @param channel
     * @return
     */
    public static String getRemoteAddress(SocketChannel channel) {
        Objects.requireNonNull(channel);

        return channel.remoteAddress().getAddress().getHostAddress();
    }

    /**
     * 获取远程客户端的端口
     * @param channel
     * @return
     */
    public static int getRemotePort(SocketChannel channel) {
        Objects.requireNonNull(channel);

        return channel.remoteAddress().getPort();
    }

    /**
     * 获取唯一ID或者IP端口信息
     * @param uniqueNo
     * @param channel
     * @return
     */
    public static String getUniqueNoOrHost(String uniqueNo, SocketChannel channel) {
        if (Objects.nonNull(uniqueNo)) {
            return uniqueNo;
        }
        StringBuilder hostSb = new StringBuilder();
        hostSb.append(channel.remoteAddress().getAddress().getHostAddress())
                .append(":")
                .append(channel.remoteAddress().getPort());
        return hostSb.toString();
    }

    /**
     * 关闭通信连接，当调用关闭失败时，强制关闭连接
     * @param channel
     * @param uniqueNo
     */
    public static void closeChannel(SocketChannel channel, String uniqueNo) {
        /**
         * 如果连接活跃，则关闭该连接
         */
        if (channel.isActive()) {
            try {
                channel.close();
            } catch (Exception e) {
                LOG.error("({}) close channel failed when deal offline event, the server will close it forcibly", uniqueNo);
                channel.unsafe().closeForcibly();
            }
        }
    }

    /**
     * 强制关闭终端连接
     * @param channel
     */
    public static void closeChannelForcibly(SocketChannel channel) {
        if (channel.isActive()) {
            channel.unsafe().closeForcibly();
        }
    }

}
