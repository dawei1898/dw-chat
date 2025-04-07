package com.dw.chat.common.exception;



/**
 * 自定义业务异常类
 *
 * @author dawei
 */

public class BizException extends RuntimeException {

    protected Integer code;

    protected String message;



    public BizException() {
        super();
    }

    public BizException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }


    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }



    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }



}
