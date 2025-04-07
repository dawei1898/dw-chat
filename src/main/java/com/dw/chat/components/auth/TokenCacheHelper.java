package com.dw.chat.components.auth;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * token缓存服务
 *
 * @author dawei
 */

@Slf4j
@Component
public class TokenCacheHelper {

    // Token 本地缓存
    private final static Cache<String, String> LOCAL_CACHE =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .concurrencyLevel(8)
                    .expireAfterWrite(AuthConstant.EXPIRE_TIME / 2, TimeUnit.MILLISECONDS)
                    .build();

    public static final long REDIS_SUCCESS = 1L;


    /**
     * 保存token
     */
    public void put(String id, String token) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        saveToLocal(id, token);
    }

    private void saveToLocal(String id, String token) {
        if (!StringUtils.isAnyEmpty(id, token)) {
            LOCAL_CACHE.put(id, token);
        }
    }


    /**
     * 是否存在
     */
    public boolean contains(String id) {
        if (StringUtils.isNotEmpty(id)) {
            if (hasOfLocal(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOfLocal(String id) {
        if (StringUtils.isNotEmpty(id)) {
            return LOCAL_CACHE.getIfPresent(id) != null;
        }
        return false;
    }


    /**
     * 删除token
     */
    public boolean remove(String id) {
        if (StringUtils.isNotEmpty(id)) {
            removeLocal(id);
        }
        return true;
    }

    private boolean removeLocal(String id) {
        if (StringUtils.isNotEmpty(id)) {
            LOCAL_CACHE.invalidate(id);
        }
        return true;
    }

}
