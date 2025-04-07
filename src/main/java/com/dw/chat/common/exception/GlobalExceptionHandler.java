package com.dw.chat.common.exception;

import com.alibaba.fastjson2.JSON;
import com.dw.chat.common.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author dawei
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Response<Void> globalException(Exception e) {
        logger.error("GlobalException:", e);
        Response<Void> response = new Response<>();
        response.setCode(Response.UNKNOWN);
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(BizException.class)
    public Response<Void> businessException(BizException e) {
        Response<Void> response = new Response<>();
        response.setCode(e.getCode() != null ? e.getCode() : Response.FAIL);
        response.setMessage(e.getMessage());
        logger.info("BizException:{}", JSON.toJSONString(response));
        return response;
    }

}
