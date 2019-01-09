package com.n3n4.im.imcore.gateway.bean.attr;

import com.n3n4.im.imcore.gateway.inf.ChannelAttr;
import io.netty.buffer.ByteBuf;

/**
 * 消息处理轨迹跟踪
 *   循环记载消息处理轨迹
 *
 */
public class PackageTraceAttr extends AbstractChannelAttr implements ChannelAttr {
    /**
     * 当前报文字节缓冲
     */
    private ByteBuf pacBuf;

    /**
     * 16进制的源报文
     */
    private String sourceHex;

    /**
     * 消息处理到哪一步
     * 0：idle处理
     * 1：消息解析
     * 2：消息校验
     * ...
     */
    private int step;

    /**
     * 是否发生异常或者错误
     */
    private boolean errorOccured;

    /**
     * 新消息到来前清理前一个消息的轨迹信息
     */
    public PackageTraceAttr refreshForNew() {
        this.pacBuf = null;
        this.sourceHex = null;

        this.step = 0;
        this.errorOccured = false;
        return this;
    }

    public PackageTraceAttr next() {
        ++ this.step;
        return this;
    }

    public ByteBuf getPacBuf() {
        return pacBuf;
    }

    public PackageTraceAttr setPacBuf(ByteBuf pacBuf) {
        this.pacBuf = pacBuf;
        return this;
    }

    public String getSourceHex() {
        return sourceHex;
    }

    public PackageTraceAttr setSourceHex(String sourceHex) {
        this.sourceHex = sourceHex;
        return this;
    }

    public int getStep() {
        return step;
    }

    public PackageTraceAttr setStep(int step) {
        this.step = step;
        return this;
    }

    public boolean isErrorOccured() {
        return errorOccured;
    }

    public PackageTraceAttr setErrorOccured(boolean errorOccured) {
        this.errorOccured = errorOccured;
        return this;
    }
}
