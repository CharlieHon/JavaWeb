package com.servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet(urlPatterns = {"/pig1", "/pig2"}, loadOnStartup = 1)
public class PigServlet extends HttpServlet {
    private int getCount = 0;
    private int postCount = 0;

    @Override
    public void init() throws ServletException {
        System.out.println("PigServlet init()...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCount++;
        System.out.println("访问方式：" + req.getMethod() + "，访问次数：" + getCount);
        // 获取访问的浏览器的IP地址 -> OOP面向对象，通过对象去找方法，子类没有找父类
        System.out.println("访问的浏览器的ip：" + req.getRemoteAddr());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postCount++;
        System.out.println("访问方式：" + req.getMethod() + "，访问次数：" + postCount);
        System.out.println("访问的浏览器的ip：" + req.getRemoteAddr());
    }
}
