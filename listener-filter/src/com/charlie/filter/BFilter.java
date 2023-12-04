package com.charlie.filter;

import javax.servlet.*;
import java.io.IOException;

public class BFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("BFilter---> 线程id= " + Thread.currentThread().getId());
        System.out.println("BFilter doFilter 的前置代码...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("BFilter doFilter 的后置代码...");
    }

    @Override
    public void destroy() {

    }
}
