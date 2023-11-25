package com.http.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class T1Servlet extends HttpServlet {
    // 这里把 doGet 和 doPost 合并处理
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果有一个请求来，重定向到hi.html
        // 1. 返回302状态码
        // 2. 响应头Location:/http/hi.html
        resp.sendRedirect("/http/hi.html"); // 重定向到 http://localhost:8080/http/hi.html
//        resp.sendRedirect("http://www.baidu.com");  // 重定向到百度页面
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
