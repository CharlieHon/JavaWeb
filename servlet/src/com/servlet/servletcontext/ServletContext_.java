package com.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletContext_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取web.xml中context-param
        // 1. 获取ServletContext对象
        ServletContext servletContext = getServletContext();
        // 2. 获取context-param参数
        String website = servletContext.getInitParameter("website");
        String career = servletContext.getInitParameter("career");
        // 3. 获取当前工程路径,如 /servlet
        String contextPath = servletContext.getContextPath();
        // 4. 获取项目发布后,在硬盘中真正的工作路径
        // / 表示项目(发布后)的 根路径 E:\javaweb\servlet\out\artifacts\servlet_war_exploded
        String realPath = servletContext.getRealPath("/");

        System.out.println("website= " + website);              // website= https://www.flyhenan.net
        System.out.println("career= " + career);                // career= 荷兰刺史
        System.out.println("项目路径:" + contextPath);           // 项目路径:/servlet
        System.out.println("项目发布后的绝对路径:" + realPath);    // 项目发布后的绝对路径:E:\javaweb\servlet\out\artifacts\servlet_war_exploded\
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
