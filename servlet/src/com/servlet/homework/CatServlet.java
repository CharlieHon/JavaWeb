package com.servlet.homework;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CatServlet implements Servlet {
    private int count = 0;  // 记录访问次数
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest hsr = (HttpServletRequest) servletRequest;
        String method = hsr.getMethod();
        System.out.println("访问方式：" + method);
        System.out.println("访问CatServlet的次数：" + (++count));
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
