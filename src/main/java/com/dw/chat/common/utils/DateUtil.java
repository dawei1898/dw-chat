package com.dw.chat.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author dawei
 */
public class DateUtil {


    public static final String FORMAT_DATE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_DATE_SECOND));
    }

    /**
     * 计算输入的时间与现在的时间差值(S)
     *
     * @param startTime 开始时间毫秒值
     * @return 耗费时间(s)
     */
    public static String getUseTime(long startTime) {
        return ((System.currentTimeMillis() - startTime) * 1.000) / 1000 + "";
    }

}
