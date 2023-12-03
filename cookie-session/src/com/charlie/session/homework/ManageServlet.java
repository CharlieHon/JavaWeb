package com.charlie.session.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/manageServlet"})
public class ManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ManageServlet 被调用...");
        HttpSession session = req.getSession();
        String check = (String) session.getAttribute("check");
        if ("validate".equals(check)) {
            String username = req.getParameter("username");
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<h1>用户管理页面</h1>");
            writer.println("欢迎你：管理员 " + username);
            writer.flush();
            writer.close();
        } else {
            System.out.println("禁止直接访问 用户管理页面");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/userlogin.html");
        }
        // 删除session中的check属性，这样即使一次登录成功，下次直接访问用户管理页面也不可以
//        session.removeAttribute("check");
        session.setAttribute("check", "invalidate");    // 设为无效,更合适
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
