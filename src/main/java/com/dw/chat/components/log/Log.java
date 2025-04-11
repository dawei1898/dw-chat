package com.dw.chat.components.log;

import java.lang.annotation.*;

/**
 * 日志追踪注解
 *
 * 1.打印入参、调用方 IP
 * 2.打印返参、接口调用时间
 *
 * @author dawei
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

}
