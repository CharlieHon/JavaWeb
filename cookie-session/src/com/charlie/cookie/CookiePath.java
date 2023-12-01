package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/cookiePath"})
public class CookiePath extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CookiePath 被调用...");
        // 1. 创建两个Cookie
        Cookie cookie = new Cookie("address", "bj");
        Cookie cookie2 = new Cookie("salary", "30000");
        // 2. 设置不同的有效路径
        // req.getContextPath() => /cs
        cookie.setPath(req.getContextPath());
        // cookie2有效路径 /cs/aaa
        cookie2.setPath(getServletContext().getContextPath() + "/aaa");

        /***   如果没有设置cookie路径，默认为 /cs 即 /构成路径          ****/

        // 3. 保存到浏览器
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        // 4. 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>设置Cookie有效路径成功~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
