package com.dw.chat.components.auth;

import java.lang.annotation.*;


/**
 * 鉴权注解
 *
 * @author dawei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

}
