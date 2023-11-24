package com.servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DogServlet extends HttpServlet {
    private int getCount = 0;
    private int postCount = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getCount++;
        System.out.println("访问方式：" + req.getMethod() + "，访问次数：" + getCount);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postCount++;
        System.out.println("访问方式：" + req.getMethod() + "，访问次数：" + postCount);
    }
}
