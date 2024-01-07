package com.charlie.ajax.servlet;

import com.charlie.ajax.entity.User;
import com.charlie.ajax.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckUserServlet2 extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("checkUserServlet2被调用...");
        // 接收jquery发送的ajax数据
        String username = req.getParameter("username");
//        System.out.println("jquery ajax username=" + username);
        resp.setContentType("text/json;charset=utf-8");
        Gson gson = new Gson();

        // 到数据库去验证，用户名是否已被占用
        User user = userService.getUserByName(username);
        if (user == null) {
            user = new User(-1, "", "", "");
        }

//        if ("king".equals(username)) {
//            user = new User(100, "king", "abc", "king@sohu.com");
//        } else {
//            // 返回一个不存在的user
//            user = new User(-1, "", "", "");
//        }

        resp.getWriter().write(gson.toJson(user));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
