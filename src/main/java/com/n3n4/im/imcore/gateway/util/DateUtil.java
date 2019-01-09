package com.n3n4.im.imcore.gateway.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author: yeanzi
 * @Date: 2018/6/4
 * @Time: 11:22
 * Email:  yeanzhi@ccclubs.com
 * 日期工具
 */
public final class DateUtil {
    private static final ZoneId ZONEID_DEFAULT = ZoneOffset.systemDefault();
    private static final DateTimeFormatter FORMATTER_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");

    public static String getNowStr() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return FORMATTER_YYYYMMDDHHMMSS.format(localDateTime);
    }

    /**
     * 获取当前时间到1970-01-01T00:00:00Z的秒数
     * @return
     */
    public static long getNowSeconds() {
        return LocalDateTime.now().atZone(ZONEID_DEFAULT).toEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println(getNowSeconds());
    }

}
