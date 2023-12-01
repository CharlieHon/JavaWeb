package com.charlie.servlet.homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示使用重定向方式获取web资源路径
 */
@WebServlet(urlPatterns = {"/myServlet02"})
public class MyServlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MyServlet02 重定向方式....");
        // 通过重定向来定位 /views/user/user.html
        /*
        1. 重定向发生在浏览器
        2. 写法1：http://localhost:8080/webpath/views/user/user.html
        3. 写法2：views/user/user.html 浏览器当前资源为登录界面 http://localhost:8080/webpath/login.html
            则该写法为 http://localhost:8080/webpath/ 拼接上 views/user/user.html
        4. 写法3：/webpath/views/user/user.html    其中 / 解析为 http://localhost:8080/
        5. 推荐写法 contextPath + "/views/user/user.html"
         */
        String contextPath = getServletContext().getContextPath();
        resp.sendRedirect(contextPath + "/views/user/user.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
