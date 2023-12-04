package com.charlie.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginCheckServlet 被调用...");
        // 获取用户名和密码，假设密码为123456就可以通过
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        if ("123456".equals(pwd)) {
            // 合法，将用户名加入session
            req.getSession().setAttribute("username", username);
            // 请求转发到 admin.html
            // 请求转发，不受过滤器的影响
            req.getRequestDispatcher("/manage/admin.html").forward(req, resp);
        } else {
            // 不合法，返回登录界面
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
