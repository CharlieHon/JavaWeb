package com.charlie.furns.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    // 判断请求是否为Ajax请求，根据请求头字段X-Requested-With判断
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
