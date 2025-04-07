package com.dw.chat.common.exception;

import com.alibaba.fastjson2.JSON;
import com.dw.chat.common.entity.Response;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author dawei
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    public Response<Void> globalException(Exception e) {
        logger.error("GlobalException:", e);
        Response<Void> response = new Response<>();
        response.setCode(Response.UNKNOWN);
        response.setMessage(e.getMessage());
        return response;
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BizException.class)
    public Response<Void> businessException(BizException e) {
        Response<Void> response = new Response<>();
        response.setCode(e.getCode() != null ? e.getCode() : Response.FAIL);
        response.setMessage(e.getMessage());
        logger.info("BizException:{}", JSON.toJSONString(response));
        return response;
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getField())
                    .append(":")
                    .append(fieldError.getDefaultMessage())
                    .append(",");
        }
        return Response.fail(Response.VALIDATE_FAIL, StringUtils.removeEnd(sb.toString(), ","));
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Void> handleConstraintViolationException(ConstraintViolationException e) {
        return Response.fail(Response.VALIDATE_FAIL, e.getMessage());
    }

}
