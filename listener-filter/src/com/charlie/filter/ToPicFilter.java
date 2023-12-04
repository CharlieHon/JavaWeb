package com.charlie.filter;

import javax.servlet.*;
import java.io.IOException;

public class ToPicFilter implements Filter {

    String[] keywords = null;   // 属性

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取禁用词
        String keyword = filterConfig.getInitParameter("keyword");
        keywords = keyword.split(",");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 解决从topic.html提交的中文乱码问题
        servletRequest.setCharacterEncoding("utf-8");   // 设置编码方式为 utf-8
        String username = servletRequest.getParameter("username");
        String comment = servletRequest.getParameter("comment");
        for (String keyword : keywords) {
            if (comment.contains(keyword)) {
                servletRequest.setAttribute("errorInfo", "评论中包含违规内容~");
                servletRequest.getRequestDispatcher("/topic.html").forward(servletRequest, servletResponse);
                return; // 返回
            }
        }
        // 继续到目标
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
