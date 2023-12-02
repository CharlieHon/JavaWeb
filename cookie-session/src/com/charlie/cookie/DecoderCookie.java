package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 解码 url编码格式 的cookie内容
 */
@WebServlet(urlPatterns = {"/decoder"})
public class DecoderCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DecoderCookie 被调用...");
        // 读取到中文cookie
        Cookie[] cookies = req.getCookies();
        Cookie nameCookie = CookieUtils.readCookieByName("name", cookies);
        String name = nameCookie.getValue();
        System.out.println("姓名：" + name);
        // 解码
        String decodeName = URLDecoder.decode(name, "utf-8");
        System.out.println("解码后：" + decodeName);

        resp.setContentType("text/html;charset=utf-8"); // 使用 utf-8编码 解码 url编码格式的内容
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>读取Cookie保存成功~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
