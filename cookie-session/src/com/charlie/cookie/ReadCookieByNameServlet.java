package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/readCookieByNameServlet"})
public class ReadCookieByNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReadCookieByNameServlet 被调用...");
        // 得到指定的cookie值
        // 1. 先得到浏览器携带的所有的cookie
        Cookie[] cookies = req.getCookies();
        // 2. 使用工具类来获取指定的cookie值
        Cookie emailcookie = CookieUtils.readCookieByName("email", cookies);
        if (emailcookie != null) {
            System.out.println("得到cookie name= " + emailcookie.getName()
            + ", cookie value= " + emailcookie.getValue());
        } else {
            System.out.println("Sorry，没有找到这个cookie！");
        }
        // 3. 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>完成读取cookie的任务...</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
