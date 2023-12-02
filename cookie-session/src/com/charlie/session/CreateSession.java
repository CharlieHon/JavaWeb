package com.charlie.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/createSession"})
public class CreateSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateSession 被调用...");
        // 1.  获取session，同时也可以创建session
        HttpSession session = req.getSession();
        // 2. 获取sessionId
        System.out.println("当前sessionId：" + session.getId());
        // 3. 给session存放数据
        session.setAttribute("email", "charlie@qq.com");
        // 4. 给浏览器发送一个回复
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>浏览器创建session成功~~</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
