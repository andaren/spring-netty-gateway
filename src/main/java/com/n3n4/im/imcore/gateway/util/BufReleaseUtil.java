package com.n3n4.im.imcore.gateway.util;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;

import java.util.Objects;

/**
 * @Author: yeanzi
 * @Date: 2018/7/20
 * @Time: 15:32
 * Email:  yeanzhi@ccclubs.com
 * buf释放工具类
 */
public final class BufReleaseUtil {

    /**
     * 循环递减计数释放buf对象
     * @param byteBuf
     */
    public static void releaseByLoop(ByteBuf byteBuf) {
        if (Objects.nonNull(byteBuf)) {
            while(byteBuf.refCnt() > 0) {
                ReferenceCountUtil.release(byteBuf);
            }
        }
    }

    /**
     * buf引用计数减一，为0时释放,但不保证为0
     * @param byteBuf
     */
    public static void releaseOnce(ByteBuf byteBuf) {
        if (Objects.nonNull(byteBuf) && byteBuf.refCnt() > 0) {
            ReferenceCountUtil.release(byteBuf);
        }
    }

}
