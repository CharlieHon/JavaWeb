package com.charlie.furns.filter;

import com.charlie.furns.entity.Member;
import com.charlie.furns.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                // 判断是否为Ajax请求
                if (!WebUtils.isAjaxRequest(req)) { // 不是Ajax请求
                    // 请求转发到登录页面，请求转发不走过滤器！
                    req.getRequestDispatcher("/views/member/login2.jsp").forward(servletRequest, servletResponse);
                    // 如果这里使用重定向，就会导致无限请求...
                } else {    // 是Ajax请求
                    // 返回一个url，以json格式返回
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("url", "views/member/login2.jsp");
                    servletResponse.getWriter().write(new Gson().toJson(resultMap));
                }
                // 直接返回，后面的语句不再执行
                return;
            } else if (!"admin".equals(member.getUsername())) {
                if (url.contains("manage")) {   // 如果该用户不是admin，但是访问了后台，则转到首页
                    req.getRequestDispatcher("/index.jsp").forward(servletRequest, servletResponse);
                }
            }
        }
        // 到这里说明用户已登录 或者 在排除范围内，则继续访问资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
