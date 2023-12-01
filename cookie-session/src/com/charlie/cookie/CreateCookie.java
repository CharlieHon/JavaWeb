package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 演示如何创建Cookie，并保存到浏览器
 */
public class CreateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateCookie 被调用...");
        // 1. 创建一个Cookie对象
        /*
        1) username 该cookie的名字，唯一，可以理解为 key
        2) charlie: 该coolie的值
        3) 可以创建多个cookie
        4) 此时cookie在服务器端，还没有到浏览器
         */
        Cookie cookie = new Cookie("username", "charlie");
        Cookie cookie2 = new Cookie("email", "charlie@qq.com");

        resp.setContentType("text/html;charset=utf-8");
        // 2. 将cookie发送给浏览器，让浏览器将该cookie保存
        resp.addCookie(cookie);
        resp.addCookie(cookie2);

        PrintWriter writer = resp.getWriter();
        writer.println("<h1>创建cookie成功~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
