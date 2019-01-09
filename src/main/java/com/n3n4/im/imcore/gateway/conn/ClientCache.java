package com.n3n4.im.imcore.gateway.conn;

import com.n3n4.im.imcore.gateway.exception.ClientMappingException;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.internal.PlatformDependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author: yeanzi
 * @Date: 2018/7/17
 * @Time: 15:14
 * Email:  yeanzhi@ccclubs.com
 * 客户端本地缓存服务
 */
public class ClientCache {
    public static final Logger LOG = LoggerFactory.getLogger(ClientCache.class);

    /**
     * 持有一个服务端channel
     */
    private static volatile Channel serverChannel;

    private static Map<String, ClientCache> UNIQUENO_TO_CLIENT = PlatformDependent.newConcurrentHashMap(1<<10);

    private static Map<Channel, ClientCache> CHANNEL_TO_CLIENT = PlatformDependent.newConcurrentHashMap(1<<10);

    /**
     * 唯一标识
     */
    private String uniqueNo;
    /**
     * 流水号
     */
    private Short serialNo;
    /**
     * 渠道
     */
    private SocketChannel channel;



    public static boolean existed(String uniqueNo, SocketChannel channel) {
        ClientCache existedInUniqueNo = UNIQUENO_TO_CLIENT.get(uniqueNo);
        if (Objects.isNull(existedInUniqueNo)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("client not exist in UNIQUENO_TO_CLIENT map when check existed: uniqueNo={}", uniqueNo);
            }
            return false;
        }
        return true;
    }

    public static boolean sameChannel(String uniqueNo, SocketChannel channel) {
        ClientCache existedInUniqueNo = UNIQUENO_TO_CLIENT.get(uniqueNo);
        if (Objects.isNull(existedInUniqueNo)) {
            throw new ClientMappingException("client not exist in UNIQUENO_TO_CLIENT map when check same: uniqueNo=" + uniqueNo);
        }

        return existedInUniqueNo.getChannel().equals(channel);
    }

    public static Optional<ClientCache> getByUniqueNo(String uniqueNo) {
        Objects.requireNonNull(uniqueNo);

        return Optional.ofNullable(UNIQUENO_TO_CLIENT.get(uniqueNo));
    }

    public static Optional<ClientCache> getByChannel(SocketChannel channel) {
        Objects.requireNonNull(channel);

        return Optional.ofNullable(CHANNEL_TO_CLIENT.get(channel));
    }

    public static ClientCache ofNew(String uniqueNo, SocketChannel channel) {
        ClientCache newClient = new ClientCache();
        newClient.setChannel(channel).setUniqueNo(uniqueNo).setSerialNo((short)0);
        return newClient;
    }

    /**
     * 因为对于终端来说，不存在“更新连接”这么一说，只有上线和下线，所谓的更新连接也是【表情】一次下线和一次上线的处理
     *     如果服务端需要更新连接以节省ClientCache的创建和销毁时再使用该方法
     * @param newChannel
     */
    @Deprecated
    public void updateClient(SocketChannel newChannel) {
        SocketChannel oldChannel = this.channel;
        removeChannelMapping(oldChannel);
//        closeChannelFocely(oldChannel);

        this.channel = newChannel;
    }

//    private void closeChannelFocely(SocketChannel channel) {
//        ChannelAttributeUtil.getLifeTrack(channel).setLiveStatus(ChannelLiveStatus.OFFLINE_UPDATE);
//        channel.pipeline().fireChannelInactive();
//    }


    private void removeChannelMapping(SocketChannel channel) {
        CHANNEL_TO_CLIENT.remove(channel);
    }

    public void addToMapping() {
        /**
         * 验证重复
         */
        boolean existed = existed(this.uniqueNo, this.channel);
        if (existed) {
            throw new ClientMappingException("client existed when add to mapping: uniqueNo=" + this.uniqueNo);
        }
        UNIQUENO_TO_CLIENT.put(this.uniqueNo, this);
        CHANNEL_TO_CLIENT.put(this.channel, this);
    }

    public void delFromMapping() {
        /**
         * 验证重复
         */
        boolean existed = existed(this.uniqueNo, this.channel);
        if (!existed) {
            throw new ClientMappingException("client not existed when delete mapping: uniqueNo=" + this.uniqueNo);
        }
        UNIQUENO_TO_CLIENT.remove(this.uniqueNo);
        CHANNEL_TO_CLIENT.remove(this.channel);
    }


    public String getUniqueNo() {
        return uniqueNo;
    }

    public ClientCache setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
        return this;
    }

    public Short getSerialNo() {
        return serialNo;
    }

    public ClientCache setSerialNo(Short serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public ClientCache setChannel(SocketChannel channel) {
        this.channel = channel;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientCache that = (ClientCache) o;

        if (!uniqueNo.equals(that.uniqueNo)) return false;
        if (!serialNo.equals(that.serialNo)) return false;
        return channel.equals(that.channel);
    }

    @Override
    public int hashCode() {
        int result = uniqueNo.hashCode();
        result = 31 * result + serialNo.hashCode();
        result = 31 * result + channel.hashCode();
        return result;
    }

    /**************************************************************************************************
     * 以下方法不要在业务上调用，仅调试用
     * ************************************************************************************************
     */

    @Deprecated
    public static Set<String> getAllKeySet() {
        return UNIQUENO_TO_CLIENT.keySet();
    }

    @Deprecated
    public static Integer getAll() {
        return UNIQUENO_TO_CLIENT.size();
    }

    @Deprecated
    public static Integer getAllMap() {
        return CHANNEL_TO_CLIENT.size();
    }

    @Deprecated
    public static ClientCache getDetail(String uniqueNo) {
        return UNIQUENO_TO_CLIENT.get(uniqueNo);
    }

    @Deprecated
    public static Set<String> getAllUniqueNo() {
        return UNIQUENO_TO_CLIENT.keySet();
    }

    @Deprecated
    public static Set<String> getAllDisConnected() {
        Set<String> uniqueNos = new HashSet<>();
        UNIQUENO_TO_CLIENT.forEach((no, client) -> {
            if (!client.getChannel().isActive()) {
                uniqueNos.add(no);
            }
        });
        return uniqueNos;
    }

    public static void setServerChannel(Channel serverChannel) {
        ClientCache.serverChannel = serverChannel;
    }

    public static Channel getServerChannel() {
        return serverChannel;
    }
}
