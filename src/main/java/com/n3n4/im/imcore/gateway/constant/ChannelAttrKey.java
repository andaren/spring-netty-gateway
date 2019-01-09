package com.n3n4.im.imcore.gateway.constant;

import com.n3n4.im.imcore.gateway.bean.attr.AbstractChannelAttr;
import com.n3n4.im.imcore.gateway.bean.attr.ChannelStatusAttr;
import com.n3n4.im.imcore.gateway.bean.attr.DefaultChannelHealthyAttr;
import com.n3n4.im.imcore.gateway.bean.attr.PackageTraceAttr;
import com.n3n4.im.imcore.gateway.util.ChannelUtils;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * @Author: yeanzi
 * @Date: 2018/5/4
 * @Time: 15:33
 * Email:  yeanzhi@ccclubs.com
 * Channel中共享变量单元的key常量
 */
public enum ChannelAttrKey {

    /**
     * 报文轨迹信息
     */
    PACKAGE_TRACE("channel.attr.pac.trace") {
        @Override
        public void initValue(SocketChannel channel) {
            PackageTraceAttr packageTraceAttr = new PackageTraceAttr();

            channel.attr(getAttributeKey()).setIfAbsent(packageTraceAttr);
        }
    },

    /**
     * channel健康数据
     */
    CHANNEL_HEALTHY("channel.attr.healthy.default") {
        @Override
        public void initValue(SocketChannel channel) {
            DefaultChannelHealthyAttr channelHealthyAttr = new DefaultChannelHealthyAttr();
            channelHealthyAttr.setCurrentClientAddress(ChannelUtils.getRemoteAddress(channel))
                    .setCurrentServerAddress(ChannelUtils.getLocalAddress(channel));
            channel.attr(getAttributeKey()).setIfAbsent(channelHealthyAttr);
        }
    },

    /**
     * channel生命状态
     */
    CHANNEL_STATUS("channel.attr.status") {
        @Override
        public void initValue(SocketChannel channel) {
            ChannelStatusAttr channelStatusAttr = new ChannelStatusAttr();
            channelStatusAttr.setChannelLiveStage(ChannelLiveStatus.ONLINE_CREATE.getCode())
                    .setCurrentStatus(ChannelLiveStatus.ONLINE_CREATE)
                    .setCreateTime(LocalDateTime.now());

            channel.attr(getAttributeKey()).setIfAbsent(channelStatusAttr);
        }
    };

    /**
     * 键名称
     */
    private String keyName;
    /**
     * 键值
     */
    private AttributeKey<? extends AbstractChannelAttr> attributeKey;
    ChannelAttrKey(String keyName) {
        this.keyName = keyName;
        this.attributeKey = AttributeKey.newInstance(keyName);
    }

    /**
     * 为Channel初始化创建所有的channelAttributeKey
     * @param channel   需要被创建的channel
     */
    public static void initForAll(SocketChannel channel) {
        Objects.requireNonNull(channel);

        for (ChannelAttrKey key :
                ChannelAttrKey.values()) {
            key.initValue(channel);
        }
    }

    /**
     * 在客户端首次进入系统的时候
     * 给所有channelAttribute添加vin等信息
     * @param channel
     * @param uniqueNo
     */
    public static void initUniqueNoForAll(SocketChannel channel, String uniqueNo) {
        Objects.requireNonNull(channel);

        for (ChannelAttrKey key :
                ChannelAttrKey.values()) {
            key.attributeValue(channel)
                    .setUniqueNo(uniqueNo);
        }
    }

    /**
     * 定义channel初始化AttributeValue的抽象方法
     * @param channel
     */
    abstract void initValue(SocketChannel channel);

    public String getKeyName() {
        return keyName;
    }

    public AttributeKey getAttributeKey() {
        return attributeKey;
    }

    public <T extends AbstractChannelAttr> T attributeValue (SocketChannel channel) {
        Objects.requireNonNull(channel);

        Attribute<? extends AbstractChannelAttr> attribute = channel.attr(getAttributeKey());
        return (T)attribute.get();
    }

}

