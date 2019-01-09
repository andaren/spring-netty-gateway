package com.n3n4.im.imcore.gateway.util;

import io.netty.buffer.ByteBuf;

import java.util.Objects;

/**
 * @Author: yeanzi
 * @Date: 2018/5/31
 * @Time: 14:22
 * Email:  yeanzhi@ccclubs.com
 * 包校验工具
 */
public final class PacValidUtil {

    /**
     * 异或的方式校验数据包
     * @param sourceBuf     原始字节buffer
     * @param skipedBytes   跳过的字节数
     * @param len           总共连续异或的字节数
     * @param compairedByte 被对比字节
     * @return
     */
    public static boolean xor(ByteBuf sourceBuf, Integer skipedBytes, Integer len, byte compairedByte) {
        sourceBuf.resetReaderIndex();
        // 参数检查
        Objects.requireNonNull(sourceBuf);
        if (skipedBytes > sourceBuf.readableBytes()) {
            throw new IllegalArgumentException("被校验的开始位置超过可读字节总数");
        }
        if (len > sourceBuf.readableBytes()) {
            throw new IllegalArgumentException("校验的字节数超过可读字节总数");
        }

        // 逐位异或
        byte destByte = (byte) 0;
        sourceBuf.skipBytes(skipedBytes);
        for (int i = 0; i < len; i++) {
            destByte ^= sourceBuf.readByte();
        }

        sourceBuf.resetReaderIndex();
        return compairedByte == destByte;
    }

    /**
     * 从当前数据包计算校验码
     * @param msg               原始消息
     * @param skipedStartBytes  从开始部位跳过的字节数
     * @param skipedEndBytes    从结尾处跳过的字节数
     * @return
     */
    public static byte caculateValidByteFromBuff(ByteBuf msg, Integer skipedStartBytes, Integer skipedEndBytes) {
        msg.resetReaderIndex();
        // 跳过指定个数字节
        msg.skipBytes(skipedStartBytes);
        // 命令单元的第一个字节
        byte currValidByte = msg.readByte();
        // 异或至倒数第二个字节
        while (msg.readableBytes() > skipedEndBytes) {
            currValidByte ^= msg.readByte();
        }
        // 重置读指针
        msg.resetReaderIndex();
        return currValidByte;
    }
}
