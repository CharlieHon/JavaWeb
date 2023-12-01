package com.charlie.cookie;

import javax.servlet.http.Cookie;

public class CookieUtils {
    // 返回指定名字的cookie值
    public static Cookie readCookieByName(String name, Cookie[] cookies) {
        // 判断传入的参数是否正确
        if (name == null || "".equals(name) || cookies == null || cookies.length == 0) {
            return null;    // 传入参数有误
        }
        // 遍历cookies
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;    // 没有找到指定的cookie
    }
}
