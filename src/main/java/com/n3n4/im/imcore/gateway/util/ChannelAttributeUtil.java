package com.n3n4.im.imcore.gateway.util;

import com.n3n4.im.imcore.gateway.bean.attr.AbstractChannelHealthyAttr;
import com.n3n4.im.imcore.gateway.bean.attr.ChannelStatusAttr;
import com.n3n4.im.imcore.gateway.bean.attr.PackageTraceAttr;
import com.n3n4.im.imcore.gateway.constant.ChannelAttrKey;
import com.n3n4.im.imcore.gateway.inf.ChannelAttr;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.Attribute;

import java.util.Objects;

/**
 * @Author: yeanzi
 * @Date: 2018/5/4
 * @Time: 16:27
 * Email:  yeanzhi@ccclubs.com
 * 渠道共享属性工具类
 */
public class ChannelAttributeUtil {

    public static PackageTraceAttr getTrace(Channel channel) {
        Objects.requireNonNull(channel);

        Attribute<PackageTraceAttr> pacProcessTrackAttribute = channel.attr(ChannelAttrKey.PACKAGE_TRACE.getAttributeKey());
        return checkNullForAttr(pacProcessTrackAttribute.get());
    }

    public static <T extends AbstractChannelHealthyAttr> T getHealthy (SocketChannel channel) {
        Objects.requireNonNull(channel);

        Attribute<T> healthyInfoAttribute = (Attribute<T>) channel.attr(ChannelAttrKey.CHANNEL_HEALTHY.getAttributeKey());
        return checkNullForAttr(healthyInfoAttribute.get());
    }

    public static ChannelStatusAttr getStatus(SocketChannel channel) {
        Objects.requireNonNull(channel);

        Attribute<ChannelStatusAttr> channelStatusAttrAttribute = channel.attr(ChannelAttrKey.CHANNEL_STATUS.getAttributeKey());
        return checkNullForAttr(channelStatusAttrAttribute.get());
    }

    private static <T extends ChannelAttr> T checkNullForAttr(T attr) {
        if (Objects.isNull(attr)) {
            throw new IllegalStateException("channel attribute values is empty");
        }
        return attr;
    }

}
