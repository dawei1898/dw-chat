package com.dw.chat.components.auth;

import com.alibaba.fastjson2.JSON;
import com.dw.chat.common.exception.BizException;
import com.dw.chat.common.utils.SpringContextHolder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权授权工具类
 *
 * @author dawei
 */
public class AuthUtil {

    public static final TokenCacheHelper tokenCacheHelper;

    static {
        tokenCacheHelper = SpringContextHolder.getBean(TokenCacheHelper.class);
    }


    /**
     * 生成token
     *
     * @param loginUser 登录用户信息
     * @return token
     */
    public static String buildToken(LoginUser loginUser) {
        if (loginUser == null) {
            throw new BizException("loginUser is null !");
        }
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(AuthConstant.LOGIN_USER, JSON.toJSONString(loginUser));
        String token = JwtUtils.createToken(claimsMap);
        putToken(loginUser.getTokenId(), token);
        return token;
    }

    /**
     * 解析token
     *
     * @param token 鉴权token
     * @return 登录用户信息
     */
    public static LoginUser parseToken(String token) {
        LoginUser loginUser = doParseToken(token);
        if (loginUser != null && hasToken(loginUser.getTokenId())) {
            return loginUser;
        }
        return null;
    }


    /**
     * 解析token
     */
    private static LoginUser doParseToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BizException("token is null !");
        }
        Claims claims = JwtUtils.parseToken(token);
        Object obj = claims.get(AuthConstant.LOGIN_USER);
        if (obj instanceof String) {
            return JSON.parseObject((String) obj, LoginUser.class);
        }
        return null;
    }


    /**
     * token是否存在缓存中
     */
    private static boolean hasToken(String tokenId) {
        return tokenCacheHelper.contains(tokenId);
    }

    /**
     * 缓存token
     *
     * @param tokenId token id
     * @param token
     */
    private static void putToken(String tokenId, String token) {
        tokenCacheHelper.put(tokenId, token);
    }

    /**
     * 删除缓存的token
     *
     * @param tokenId token id
     */
    public static boolean removeToken(String tokenId) {
        return tokenCacheHelper.remove(tokenId);
    }

}
