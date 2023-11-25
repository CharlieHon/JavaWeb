package com.servlet.servletcontext;

import javax.servlet.ServletContext;

public class WebUtils {
    public static Integer visitCount(ServletContext servletContext) {
        // 从servletContext获取 visit_count 属性 k-v
        // 判断 visit_count是否为null
        Object visitCount = servletContext.getAttribute("visit_count");
        if (visitCount == null) {   // 如果为null,说明是第一次访问
            servletContext.setAttribute("visit_count", 1);  // "visit_count" - Integer
            visitCount = 1; // 自动装箱
        } else {
            // 去除visitCount属性的值 + 1
            visitCount = Integer.parseInt(visitCount + "") + 1;
            servletContext.setAttribute("visit_count", visitCount);
        }

        return Integer.parseInt(visitCount + "");
    }
}
