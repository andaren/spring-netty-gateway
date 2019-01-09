package com.n3n4.im.imcore.gateway.bean.track;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * @Author: yeanzi
 * @Date: 2018/5/4
 * @Time: 14:26
 * Email:  yeanzhi@ccclubs.com
 * 消息处理链路跟踪
 */
public class PacProcessTrack {
    /**
     * 消息中的唯一区分码
     */
    private String uniqueNo;

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
    private int step;       // 默认在第一步

    /**
     * 是否发生异常或者错误
     */
    private boolean errorOccur;

    private HandlerPacTrack[] handlerPacTracks;

    public PacProcessTrack next() {
        ++ this.step;
        return this;
    }

    public PacProcessTrack reset() {
        this.uniqueNo = null;
        this.errorOccur = false;
        this.step = 0;
        this.sourceHex = null;
        if (handlerPacTracks !=null && handlerPacTracks.length > 0) {
            Arrays.stream(handlerPacTracks).forEach(ht -> {
                ht.reset();
            });
        }
        return this;
    }

    public HandlerPacTrack getCurrentHandlerTracker() {
        if (step < 0 || step > handlerPacTracks.length) {
            throw new IllegalArgumentException("get current handlerTracker failed for the invalid value step: " + step);
        }
        return handlerPacTracks[step];
    }

    public HandlerPacTrack getPreHandlerTracker() {
        if (step - 1 < 0 || step > handlerPacTracks.length) {
            throw new IllegalArgumentException("get current handlerTracker failed for the invalid value step: " + step);
        }
        return handlerPacTracks[step - 1];
    }

    // ------------------------------------------------------------------------
    public String getUniqueNo() {
        return uniqueNo;
    }

    public PacProcessTrack setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
        return this;
    }

    public int getStep() {
        return step;
    }

    public PacProcessTrack setStep(int step) {
        this.step = step;
        return this;
    }

    public boolean isErrorOccur() {
        return errorOccur;
    }

    public PacProcessTrack setErrorOccur(boolean errorOccur) {
        this.errorOccur = errorOccur;
        return this;
    }

    public String getSourceHex() {
        return sourceHex;
    }

    public PacProcessTrack setSourceHex(String sourceHex) {
        this.sourceHex = sourceHex;
        return this;
    }

    public HandlerPacTrack[] getHandlerPacTracks() {
        return handlerPacTracks;
    }

    public PacProcessTrack setHandlerPacTracks(HandlerPacTrack[] handlerPacTracks) {
        this.handlerPacTracks = handlerPacTracks;
        return this;
    }

    public ByteBuf getPacBuf() {
        return pacBuf;
    }

    public PacProcessTrack setPacBuf(ByteBuf pacBuf) {
        this.pacBuf = pacBuf;
        return this;
    }
}
