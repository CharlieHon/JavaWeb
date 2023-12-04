package com.charlie.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HiServlet 被调用...");
        // 给ServletContext对象操作属性
        ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("name", "Bruce");
        servletContext.setAttribute("name", "Leslie");
        servletContext.removeAttribute("name");

        // 获取session
        HttpSession session = req.getSession();
        session.setAttribute("age", 100);   // 添加属性
        session.setAttribute("age", 96);    // 修改属性
        session.removeAttribute("age");         // 删除属性
        System.out.println("HiServlet 处理完毕...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
