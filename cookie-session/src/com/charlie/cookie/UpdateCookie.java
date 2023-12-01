package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/updateCookie"})
public class UpdateCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UpdateCookie 被调用...");
        // 演示如何修改 cookie
        // 1. 根据名字查找cookie
        String cookieName = "email";
        Cookie[] cookies = req.getCookies();

        Cookie userNameCookie = new Cookie("username", "Yann LeCun");

        Cookie cookie = CookieUtils.readCookieByName(cookieName, cookies);
        if (cookie == null) {   // 在浏览器没有email cookie
            System.out.println("当前访问服务器的浏览器没有 该cookie");
        } else {
            cookie.setValue("Charlie-Hi~");
            // 此时浏览器的cookie并未改变，需要返回才行
        }
        // 2. 遍历cookie
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie1 : cookies) {
                System.out.println("cookie1 name= " + cookie1.getName()
                + ", value= " + cookie1.getValue());
            }
        }
        // 3. 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        // 4. 如果希望本地的浏览器cookie也修改，需要使用 resp.addCookie(cookie);
        if (cookie != null) {
            resp.addCookie(cookie);
        }
        if (userNameCookie != null) {
            // 把新创建的userNameCookie重新保存到浏览器
            // 如果保存的userNameCookie和已经存在的cookie同名，则等价于修改该同名cookie
            System.out.println("修改为自定义的cookie");
            resp.addCookie(userNameCookie);
        }
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>完成修改cookie的任务...</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
