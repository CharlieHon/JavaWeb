package com.charlie.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet 被调用...");

        // realPath 是项目的工作路径
        String realPath = getServletContext().getRealPath("/");
        System.out.println("realPath= " + realPath);    // realPath= E:\javaweb\webpath\out\artifacts\webpath_war_exploded\

        // contextPath 是tomcat配置的 application context
        // 将来可以在服务器渲染技术 jsp/thymeleaf 动态获取工程路径
        String contextPath = getServletContext().getContextPath();
        System.out.println("contextPath= " + contextPath);  // contextPath= /webpath
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
