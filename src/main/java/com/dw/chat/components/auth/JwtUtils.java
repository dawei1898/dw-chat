package com.dw.chat.components.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Map;

/**
 * Jwt工具类
 *
 * @author dawei
 */
public class JwtUtils {

    public static SecretKey SECRET_KEY = Keys.hmacShaKeyFor(AuthConstant.SECRET.getBytes());


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder().signWith(SECRET_KEY).claims(claims).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build()
                .parseSignedClaims(token).getPayload();
    }


}
