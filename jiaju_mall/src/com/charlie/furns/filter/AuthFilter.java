package com.charlie.furns.filter;

import com.charlie.furns.entity.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 这是用于权限验证的过滤器，对指定的url进行检验
 * 如果登录过，就放行；如果没有登录，就返回到登录页面
 */
public class AuthFilter implements Filter {

    // 把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取到配置的 excludedUrls
        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] splitUrl = strExcludedUrls.split(",");
        // 将 String[] 转成 List<String>
        excludedUrls = Arrays.asList(splitUrl);
        //System.out.println("excludedUrls=" + excludedUrls); // [/views/manage/manage_login.jsp,  /views/member/login2.jsp]
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        // 等到用户请求的url
        String url = req.getServletPath();
        //System.out.println("url=" + url);   // url=/views/member/login2.jsp

        // 判断是否需要验证，如果请求的url不在排除范围，则走过滤的逻辑
        if (!excludedUrls.contains(url)) {
            // 得到session中的member对象
            Member member = (Member) req.getSession().getAttribute("member");
            if (null == member) {   // 如果成立，说明未登录
                // 请求转发到登录页面，请求转发不走过滤器！
                req.getRequestDispatcher("/views/member/login2.jsp").forward(servletRequest, servletResponse);
                // 如果这里使用重定向，就会导致无限请求...
                // 直接返回，后面的语句不再执行
                return;
            }
        }
        // 到这里说明用户已登录 或者 在排除范围内，则继续访问资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
