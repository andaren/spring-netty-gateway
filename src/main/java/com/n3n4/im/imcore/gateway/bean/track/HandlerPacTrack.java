package com.n3n4.im.imcore.gateway.bean.track;

/**
 * @Author: yeanzi
 * @Date: 2018/5/4
 * @Time: 14:51
 * Email:  yeanzhi@ccclubs.com
 * 记录消息在每一步的handler中的处理状态
 */
public class HandlerPacTrack {
    /**
     * 该handler处理该消息实际用的时间（ms）
     */
    private long usedTime;

    /**
     * 是否发生异常或者错误
     */
    private boolean errorOccur;

    public HandlerPacTrack reset() {
        this.usedTime = 0L;
        this.errorOccur = false;
        return this;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public HandlerPacTrack setUsedTime(long usedTime) {
        this.usedTime = usedTime;
        return this;
    }

    public boolean isErrorOccur() {
        return errorOccur;
    }

    public HandlerPacTrack setErrorOccur(boolean errorOccur) {
        this.errorOccur = errorOccur;
        return this;
    }
}
