package com.charlie.session.homework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/loginCheck"})
public class LoginCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginCheckServlet 被调用...");
        // 登录检测，只要用户名不为空，且密码为666666，就认为登录成功
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        HttpSession session = req.getSession();
//        String contextPath = getServletContext().getContextPath();
        if (username != null && !username.isEmpty() && "666666".equals(pwd)) {
            session.setAttribute("check", "validate");
//            resp.sendRedirect(contextPath + "/manageServlet");  // 重定向无法获取输入的参数
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/manageServlet");
            requestDispatcher.forward(req, resp);   // 请求转发

        } else {
            session.setAttribute("check", "invalidate");
//            resp.sendRedirect(contextPath + "/error.html");
            req.getRequestDispatcher("/error.html").forward(req, resp); // 这里也使用请求转发
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
