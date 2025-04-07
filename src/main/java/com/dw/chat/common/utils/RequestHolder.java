package com.dw.chat.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 获取HttpServletRequest
 *
 * @author dawei
 */
@Slf4j
public class RequestHolder {

	private static final String UNKNOWN = "unknown";

	/**
	 * 获取HttpServletRequest请求
	 *
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			return requestAttributes.getRequest();
		}
		return null;
	}

	/**
	 * 获取请求IP
	 *
	 * @return String IP
	 */
	public static String getHttpServletRequestIpAddress() {
		HttpServletRequest request = getHttpServletRequest();
		return getHttpServletRequestIpAddress(request);
	}

	public static String getHttpServletRequestIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(",")) {
			ip = ip.split(",")[0];
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	public static String getServerHttpRequestIpAddress(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		String ip = headers.getFirst("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
			if (ip.contains(",")) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = headers.getFirst("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = headers.getFirst("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = headers.getFirst("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	 * 获取请求体
	 */
	public static byte[] getBoyBytes(){
		byte[] bytes = new byte[0];
		try {
			HttpServletRequest httpServletRequest = getHttpServletRequest();
			if (httpServletRequest != null) {
				InputStream inputStream = httpServletRequest.getInputStream();
				bytes = StreamUtils.copyToByteArray(inputStream);
			}
		} catch (IOException e) {
			log.error("Failed to getBoyBytes.", e);
		}
		return bytes;
	}

	public static String getHeader(String headerName){
		HttpServletRequest httpServletRequest = getHttpServletRequest();
		if (httpServletRequest == null || StringUtils.isEmpty(headerName)) {
			return null;
		}
		return httpServletRequest.getHeader(headerName);
	}
}
