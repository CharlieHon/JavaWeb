package com.charlie.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 1. filter在web项目启动时，由tomcat来创建filter实例，只会创建一个
 * 2. 会调用filter默认的无参构造器，同时会调用init方法，只会调用一次
 * 3. 在创建filter实例时，同时会创建一个FilterConfig对象，并通过init方法传入
 * 4. 通过FilterConfig对象，程序员可以获取该filter的相关配置信息
 * 5. 当一个http请求和该filter的url-pattern匹配时，就会调用doFilter房啊
 * 6. 在调用doFilter方法时，tomcat会同时创建servletRequest和servletResponse和FilterChain对象，
 *      并通过doFilter传入
 * 7. 如果后面的请求目标资源(jsp,servlet...)会使用到req和resp，那么会继续传递使用
 * 8. javaweb - ssm - sprintboot 有浏览器和web服务器(tomcat)参与
 */
public class ManageFilter implements Filter {

    private int count = 0;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 当Tomcat创建Filter后，会调用该方法进行初始化
        // 有了filter机制，可以理解在调用servlet之前，先匹配filter
        /*
        1. 根据req对象封装的uri
        2. 到filterUrlMapping去匹配
        3. 如果匹配上就调用 filterMapping 对应的 filter对象的 doFilter
        4. 如果没有匹配上，就直接走后续的servlet/jsp/html
         */
        System.out.println("ManageFilter init 被调用...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 当每次调用该filter时，doFilter方法就会被调用
        System.out.println("ManageFilter doFilter 被调用 " + (++count));
        // 如果这里，没有调用继续请求的方法，则就停止
        // 在调用过滤器前，req对象已经被创建并封装
        // 所以在这里可以通过servletRequest获取很多信息，比如访问url，session，访问参数等等...
        // 就可以做事务管理、数据获取、日志管理等

        // 获取session
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        if (username != null) {
            // 用户登录成功，直接放行
            /*
            1. doFilter() 表示继续访问目标资源url
            2. servletRequest和servletResponse对象会传递给目标资源/文件
            3. 一定要理解filter传递的两个对象，在后面的 servlet/jsp 是同一个对象(指的是同一次http请求)
             */
            System.out.println("======日志信息======");
            System.out.println("访问的用户名= " + username.toString());
            System.out.println("访问的url= " + httpServletRequest.getRequestURL());
            System.out.println("访问的IP= " + httpServletRequest.getRemoteAddr());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {    // 说明没有登录过，回到登录界面
            servletRequest.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        // 当filter对象被销毁时，该方法被调用
        System.out.println("ManageFilter destroy 被调用...");
    }
}
