package com.charlie.furns.filter;

import com.charlie.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;

// 管理事务的过滤器
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtilsByDruid.commit();      // 统一提交
        } catch (Exception e) { // 出现异常
            JDBCUtilsByDruid.rollback();    // 回滚事务
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {}
}
