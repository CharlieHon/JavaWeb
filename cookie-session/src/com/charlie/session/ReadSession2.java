package com.charlie.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/readSession2"})
public class ReadSession2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReadSession2 被调用...");
        // 1. 获取session
        HttpSession session = req.getSession();
        // 如果在有效期，则sid与CreateSession2下的相同；如果被销毁，则上语句会重新创建session，此时sid不一样
        System.out.println("ReadSession2 sid: " + session.getId());
        // 2. 读取session的属性
        Object u = session.getAttribute("u");
        if (u != null) {
            System.out.println("读取到session属性");
            System.out.println("u: " + (String) u);
        } else {
            System.out.println("未读取到session，说明原session已被销毁");
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>session创建成功，生命周期为60s~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
