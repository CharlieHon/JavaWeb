package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 读取从浏览器发送来的cookie[底层仍然是http协议]
 */
@WebServlet(urlPatterns = {"/readCookies"})
public class ReadCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReadCookies 被调用...");
        // 1. 通过req对象读取cookies信息
        Cookie[] cookies = req.getCookies();
        // 2. 遍历cookie
        System.out.println("cookies:");
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                System.out.println("name: " + cookie.getName()
                + ", value: " + cookie.getValue());
            }
        }
        // 3. 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>读取cookie信息成功~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
