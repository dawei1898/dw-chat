package com.dw.chat.common.utils;


import com.dw.chat.common.entity.Response;
import com.dw.chat.common.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * 参数校验工具类
 *
 * @author dawei
 */
public class ValidateUtil {

    private static final Integer CODE = Response.VALIDATE_FAIL;

    private static final String DEFAULT_MSG = "参数不能为空！";


    public static void fail(String message) {
        throw new BizException(CODE, message);
    }

    public static void isFalse(boolean flag, String message) {
        isTure(!flag, message);
    }

    public static void isTure(boolean flag) {
        isTure(flag, DEFAULT_MSG);
    }

    public static void isTure(boolean flag, String message) {
        if (flag) {
            throw new BizException(CODE, message);
        }
    }

    public static void isNull(Object obj) {
        isNull(obj, DEFAULT_MSG);
    }

    public static void isNull(Object obj, String message) {
        if (obj == null) {
            throw new BizException(CODE, message);
        }
    }

    public static void isEmpty(String str) {
        isEmpty(str, DEFAULT_MSG);
    }

    public static void isEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new BizException(CODE, message);
        }
    }

    public static void isEmpty(Collection<?> collection) {
        isEmpty(collection, DEFAULT_MSG);
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(CODE, message);
        }
    }

    public static void isEmpty(Object[] array) {
        isEmpty(array, DEFAULT_MSG);
    }

    public static void isEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BizException(CODE, message);
        }
    }

}
