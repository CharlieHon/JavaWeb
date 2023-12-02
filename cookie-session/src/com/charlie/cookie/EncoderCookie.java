package com.charlie.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(urlPatterns = {"/encoder"})
public class EncoderCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EncoderCookie 被调用...");
        // 1. 创建cookie，带中文
        /*
        1. 如果直接存放中文cookie，无论是name还是value
        2. 解决方法：将中文编码为URL编码
        3. 编码后再保存即可
         */
        // 将指定编码方式(utf-8)的中文内容 转化为 URL编码格式(%E6%9D%8E%E5%B0%8F%E9%BE%99)
        String nameVal = URLEncoder.encode("李小龙", "utf-8");
        System.out.println("李小龙：" + nameVal);
        Cookie cookie = new Cookie("name", nameVal);
        // 2. 保存cookie到浏览器
        resp.addCookie(cookie);
        // 3. 给浏览器返回信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>中文Cookie保存成功~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
