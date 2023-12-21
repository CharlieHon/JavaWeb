package com.charlie.ajax.servlet;

import com.charlie.ajax.entity.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("CheckUserServlet 被调用...");

        // 接收ajax提交的数据，参数要与ajax提交的数据匹配
        // xhr.open("GET", "/ajax/checkUserServlet?username=" + uname, true);
        String username = req.getParameter("username");
        System.out.println("username: " + username);

        resp.setContentType("text/html;charset=utf-8");
        // 假定用户名为king，就不可用，其它用户名可以
        if ("king".equals(username)) {
            // 以后这个信息，是从DB获取
            User king = new User(100, "king", "666", "king@sohu.com");
            // 转成json
            String strKing = new Gson().toJson(king);
            // 返回
            resp.getWriter().write(strKing);
        } else {
            // 如果用户名可以用，返回 ""
            resp.getWriter().write("");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
