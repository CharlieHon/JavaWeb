package com.charlie.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet03 进行转发...");
        // 通过转发来定位到 b.html
        /*
        1. 因为请求转发是在服务器端进行解析的，此时第一个 / 会被解析成
            http://ip:port/项目名(application context)/
        2. "/d1/d2/b.html" => http://localhost:8080/webpath/d1/d2/b.html
         */
        req.getRequestDispatcher("/d1/d2/b.html").forward(req, resp);
        // 3. 在服务器进行转发时，没有 / 就按照默认的方式参考定位 http://localhost:8080/webpath/
//        req.getRequestDispatcher("d1/d2/b.html").forward(req, resp);  // 效果同上
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
