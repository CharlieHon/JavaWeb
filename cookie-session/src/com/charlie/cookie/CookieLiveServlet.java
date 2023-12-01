package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

@WebServlet(urlPatterns = {"/live"})
public class CookieLiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CookieLiveServlet 被调用...");
        // 演示创建一个cookie，生命周期为60s
        Cookie cookie = new Cookie("job", "java");
        // 1. 从创建该cookie开始计时，60s后无效
        // 2. 浏览器根据创建的时间计时，计时到60s后，就认为该cookie无效
        // 3. 如果该cookie无效，那么浏览器在法http请求时，就不再携带该cookie
        cookie.setMaxAge(60);
        // 将cookie保存到浏览器
        resp.addCookie(cookie);

        // 演示如何删除一个cookie，比如删除username
        // 1. 先得到username cookie
        Cookie[] cookies = req.getCookies();
        Cookie userNameCookie = CookieUtils.readCookieByName("username", cookies);
        if (userNameCookie != null) {
            // 2. 将生命周期设置为0
            userNameCookie.setMaxAge(0);
            // 3. 重新保存该cookie，因为将生命周期设置为0，就等价于让浏览器删除该cookie
            // 4 该cookie会被浏览器直接删除 Set-Cookie: username=charlie; Expires=Thu, 01-Jan-1970 00:00:10 GMT
            resp.addCookie(userNameCookie); // 必要通知浏览器才行，返回一个 Set-Cookie: xxxx
        } else {
            System.out.println("没有找到该cookie，无法删除...");
        }

        // 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Cookie生命周期...</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
