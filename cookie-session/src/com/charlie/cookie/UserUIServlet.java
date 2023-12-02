package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class UserUIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserUIServlet 被调用...");
        Cookie[] cookies = req.getCookies();
        Cookie cookie = CookieUtils.readCookieByName("loginUser", cookies);
        String userName = "";
        if (cookie != null) {
            userName = cookie.getValue();
        }
        // 1. 得到 Writer
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>七里台男子职业技术学院</title>\n" +
                "    <base href=\"/cs/\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>登录页面</h1>\n" +
                "<form action=\"test\" method=\"post\">\n" +
                "    用户名：<input type=\"text\" name=\"username\" value=\"" + userName + "\"><br/>\n" +
                "    密&nbsp;码：<input type=\"password\" name=\"pwd\"><br/>\n" +
                "    <input type=\"submit\" value=\"登录\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
