package com.servlet.response.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyPayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PayServlet 被调用...");
        String id = req.getParameter("userid");
        String money = req.getParameter("money");
        // 浏览器不输入，结果是空字符串 ""
        System.out.println("浏览器传入的money： " + money + "，类型：" + money.getClass() + "，长度：" + money.length());
        String contextPath = getServletContext().getContextPath();
//        int m = Integer.parseInt(money);
        int m = WebUtils.parseInt(money);   // 自定义工具类，处理异常，不将异常抛给tomcat
        if (m <= 100) {
            resp.sendRedirect(contextPath + "/pay.html");
        } else {
            resp.sendRedirect(contextPath + "/payok.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
