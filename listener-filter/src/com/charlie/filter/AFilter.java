package com.charlie.filter;

import javax.servlet.*;
import java.io.IOException;

public class AFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AFilter---> 线程id= " + Thread.currentThread().getId());
        System.out.println("AFilter doFilter 的前置代码...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("AFilter doFilter 的后置代码...");
    }

    @Override
    public void destroy() {

    }
}
