package com.charlie.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 演示FilterConfig使用
 */
public class HspFilterConfig implements Filter {

    // 因为 filterConfig只在 init() 方法中获取，要想在 doFilter() 方法中使用
    // 需要使用一个属性来保存起来
    private String ip = ""; // 禁用的ip

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 通过filterConfig获取相关的参数
        String filterName = filterConfig.getFilterName();
        ip = filterConfig.getInitParameter("ip");
        ServletContext servletContext = filterConfig.getServletContext();
        // 获取该filter所有的配置参数名
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();

        // 遍历枚举
        while (initParameterNames.hasMoreElements()) {
            System.out.println("param-name: " + initParameterNames.nextElement());
        }

        System.out.println("filterName= " + filterName);
        System.out.println("ip= " + ip);
        System.out.println("servletContext= " + servletContext);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 禁用ip实例演示，禁用 128.12 网段的ip访问
        // 先获取到访问的ip地址
        String remoteAddr = servletRequest.getRemoteAddr();
        if (remoteAddr.contains(ip)) {
            System.out.println("禁用该网段的ip");
            servletRequest.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
            return; // 直接返回！
        }
        // 继续访问目标资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
