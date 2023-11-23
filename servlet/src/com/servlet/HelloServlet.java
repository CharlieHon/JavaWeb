package com.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 1. 开发Servlet需要实现Servlet接口
 * 2. 实现Servlet接口的5个方法
 */
public class HelloServlet implements Servlet {
//    private int count = 0;    // 属性
    /**
     * 1. 初始化 servlet
     * 2. 当创建 HelloServlet 实例时，会调用 init方法
     * 3. 该方法只会调用一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init() 被调用");
    }

    /**
     * 返回 ServletConfig 即返回 Servlet的配置
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 1. service方法处理浏览器的请求(包括get/post)
     * 2. 当浏览器每次请求 Servlet 时，就会调用一次service方法
     * 3. 当 Tomcat 调用该方法时，会把 http 请求的数据封装成实现了 ServletRequest接口的 request对象
     * 4. 通过 servletRequest对象，可以得到用户提交的数据
     * 5. servletResponse对象可以用于返回数据给 Tomcat->浏览器
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws ServletException, IOException {
//        count++;
        // 如果count的值，在不停地累计，说明HelloServlet是单例地
//        System.out.println("hi HelloServlet~ count=" + count);
        // Tomcat每处理一次http请求，就会产生一个新的线程去处理
//        System.out.println("当前线程id= " + Thread.currentThread().getId());
        // 思考 -> 从servletRequest对象来获取请求方式->
        /*
        1. ServletRequest 没有获取提交方式的方法
        2. ServletRequest 看看其子接口有没有相关方法
        3. 小技巧：ctrl+alt+b => 查看接口的子接口和实现子类
        4. 把 servletRequest 转成(向下转型) HttpServletRequest 引用
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
//        System.out.println("method= " + method);    // GET
        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
            doPost();
        }
    }

    // 用于响应get请求
    public void doGet() {
        System.out.println("doGet() 被调用...");
    }

    // 用于响应post请求
    public void doPost() {
        System.out.println("dePost() 被调用...");
    }

    /**
     * 返回servlet信息，使用较少
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 1. 该方法在servlet销毁时，被调用
     * 2. 只会调用一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy 被调用...");
    }
}
