package com.servlet.request.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet...");
        req.setCharacterEncoding("utf-8");  // 设置传递过来数据的编码方式
        String username = req.getParameter("username");
        String pwd1 = req.getParameter("pwd1");
        String pwd2 = req.getParameter("pwd2");
        // 获取checkbox
        String[] sports = req.getParameterValues("sport");
        String sportsStr = "";
        for (String sport : sports) {
            sportsStr += sport + " ";
        }
        // 获取 radio
        String gender = req.getParameter("gender");
        // 获取select
        String city = req.getParameter("city");
        // 获取textarea
        String info = req.getParameter("info");

        // 返回给浏览器回显
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.print("用户名：" + username + "<br/>");
        writer.print("密码1：" + pwd1 + "<br/>");
        writer.print("密码2：" + pwd2 + "<br/>");
        writer.print("性别：" + gender + "<br/>");
        writer.print("运动爱好：" + sportsStr + "<br/>");
        writer.print("城市：" + city + "<br/>");
        writer.print("个人描述：" + info + "<br/>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
