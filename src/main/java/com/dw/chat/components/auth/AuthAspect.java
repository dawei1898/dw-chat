package com.dw.chat.components.auth;

 
import com.dw.chat.common.entity.Response;
import com.dw.chat.common.exception.BizException;
import com.dw.chat.common.utils.RequestHolder;
import com.dw.chat.common.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 鉴权切面
 *  
 * @author dawei 
 */

@Slf4j
@Aspect
@Component
@Order(AuthConstant.AUTH_ORDER)
public class AuthAspect {

    @Pointcut("@annotation(com.dw.chat.components.auth.Auth)")
    public void authPointcut() {
    }

    @Around("authPointcut()")
    public Object doAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // 校验token
        try {
            LoginUser loginUser = null;
            try {
                String visitToken = RequestHolder.getHeader(AuthConstant.TOKEN_KEY);
                visitToken = StringUtils.removeStart(visitToken, AuthConstant.TOKEN_VALUE_PREFIX);
                loginUser = AuthUtil.parseToken(visitToken);
            } catch (Exception e) {
                log.error("Failed to parseToken. ", e);
                throw new BizException(Response.AUTH_FAIL, "鉴权失败！");
            }
            if (loginUser == null || loginUser.getUserId() == null){
                throw new BizException(Response.AUTH_FAIL, "鉴权失败！");
            }
            // 从token解析出来的登录用户信息, 设置到线程本地变量中
            UserContextHolder.setUser(loginUser);

            return pjp.proceed();
        } finally {
            // 清除线程本地变量
            UserContextHolder.remove();
        }
    }

}
