package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/test"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet 被调用...");
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        Cookie userNameCookie = null;
        String respContext = null;
        if ("charlie".equals(username) && "123456".equals(pwd)) {
            respContext = "<h1>登录成功</h1>";
            userNameCookie = new Cookie("loginUser", username);
            // 设置该cookie的生命周期，3天
            userNameCookie.setMaxAge(3600 * 24 * 3);
            resp.addCookie(userNameCookie);
        } else {
            respContext = "<h1>登录失败</h1>";
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println(respContext);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
