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

}
