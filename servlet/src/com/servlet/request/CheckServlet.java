package com.servlet.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CheckServlet...");

        // 根据用户名来确定该用户是什么身份
        String username = req.getParameter("username");
        // 注意：如果是同一个req对象(请求转发)，那么可以在不同的servlet中使用getParameter获得相同的内容
        if ("tom".equals(username)) {
            // 分配权限
            req.setAttribute("role", "管理员");
        } else {
            req.setAttribute("role", "普通用户");
        }

        // 获取分发器
        // 1. "/manageServlet" 表示下一个要抓发的servlet的url
        // 2. / 会被解析成 /servlet
        // 3. requestDispatcher.forward(req, resp) 表示把当前的servlet对象的req和resp对象，传递给下一个servlet
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/manageServlet");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
