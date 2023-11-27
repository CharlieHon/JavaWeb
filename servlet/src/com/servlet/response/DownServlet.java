package com.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DownServlet 被调用");
        // 完成自己的业务
        // 发出请求重定向 -> DownServletNew
        /*
        1. sendRedirect：本质会返回302状态码 和 Location: /servlet/downServletNew
        2. 因此 302 和 /servlet/downServletNew 是浏览器解析，而不是服务器
        3. 浏览器在解析 /servlet/downServletNew => http://localhost:8080/servlet/downServletNew
        4. 动态获取 application context
         */
        String contextPath = getServletContext().getContextPath();
//        System.out.println("contextPath= " + contextPath);  // contextPath= /servlet
        resp.sendRedirect(contextPath + "/downServletNew");
//        resp.sendRedirect("http://www.baidu.com");    // 重定向到工程以外的资源

        // 第二种重定向的写法
//        resp.setStatus(302);    // 设置状态码
//        resp.setHeader("Location", "/servlet/downServletNew");  // 设置响应头的 Location
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
