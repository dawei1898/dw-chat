package com.dw.chat.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;

/**
 * 获取IP
 */
@Slf4j
public class AddressUtil {
    private final static String UNKNOWN_STR = "unknown";

    /**
     * 获取客户端IP地址
     */
    public static String getRemoteIP() {
        return getRemoteIP(getHttpServletRequest());
    }

    /**
     * 获取客户端IP地址
     */
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = "";
        try {
            if (request == null) {
                return ip;
            }
            ip = request.getHeader("X-Forwarded-For");
            if (isEmptyIP(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (isEmptyIP(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                    if (isEmptyIP(ip)) {
                        ip = request.getHeader("HTTP_CLIENT_IP");
                        if (isEmptyIP(ip)) {
                            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                            if (isEmptyIP(ip)) {
                                ip = request.getRemoteAddr();
                                if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                                    // 根据网卡取本机配置的IP
                                    ip = getLocalIP();
                                }
                            }
                        }
                    }
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = ips[index];
                    if (!isEmptyIP(ip)) {
                        ip = strIp;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to getRemoteIP.", e);
        }
        return ip;
    }

    private static boolean isEmptyIP(String ip) {
        return StringUtils.isEmpty(ip) || UNKNOWN_STR.equalsIgnoreCase(ip);
    }

    /**
     * 获取本机的IP地址
     */
    public static String getLocalIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("Failed to getLocalIP.", e);
        }
        return "";
    }

    /**
     * 使用request对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                return  ((ServletRequestAttributes) requestAttributes).getRequest();
            }
        } catch (Exception e) {
            log.error("Failed to getHttpServletRequest.", e);
        }
        return null;
    }
}
