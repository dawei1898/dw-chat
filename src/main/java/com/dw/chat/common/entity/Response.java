package com.dw.chat.common.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 接口返回统一封装
 *
 * @author dawei
 */

@Data
public class Response<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 返回码 */
    private Integer code;

    /** 返回描述 */
    private String message;

    /** 返回数据 */
    private T data;



    /** 接口成功响应,业务成功受理 */
    public static final Integer SUCCESS = 200;

    /** 参数校验失败 */
    public static final Integer VALIDATE_FAIL = 400;

    /** 权限校验失败 */
    public static final Integer AUTH_FAIL = 401;

    /** 业务处理失败 */
    public static final Integer FAIL = 500;

    /** 未知错误 */
    public static final Integer UNKNOWN = 999;


    private static final String MSG_SUCCESS = "成功";

    private static final String MSG_FAIL = "失败";

    private static final String MSG_VALIDATE_FAIL = "参数校验不通过";


    public static <T> Response<T> build(Integer code, String msg) {
        return build(code, msg, null);
    }

    public static <T> Response<T> build(Integer code, String msg, Object obj) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(msg);
        if (obj != null) {
            response.setData((T) obj);
        }
        return response;
    }


    /**
     * 业务处理成功
     */
    public static <T> Response<T> success() {
        return success(MSG_SUCCESS);
    }

    public static <T> Response<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Response<T> success(Object obj) {
        return success(MSG_SUCCESS, obj);
    }

    public static <T> Response<T> success(String msg, Object obj) {
        Response<T> response = new Response<>();
        response.setCode(Response.SUCCESS);
        response.setMessage(msg);
        if (obj != null) {
            response.setData((T) obj);
        }
        return response;
    }

    /**
     * 业务处理失败
     */
    public static <T> Response<T> fail() {
        return fail(MSG_FAIL);
    }

    public static <T> Response<T> fail(String msg) {
        Response<T> response = new Response<>();
        response.setCode(Response.FAIL);
        response.setMessage(msg);
        return response;
    }

    public static <T> Response<T> fail(Integer code, String msg) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    /**
     * 参数校验失败
     */
    public static <T> Response<T> validateFail() {
        return validateFail(MSG_VALIDATE_FAIL);
    }

    public static <T> Response<T> validateFail(String msg) {
        Response<T> response = new Response<>();
        response.setCode(Response.VALIDATE_FAIL);
        response.setMessage(msg);
        return response;
    }

}