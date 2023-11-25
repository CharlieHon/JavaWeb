package com.http.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>登录成功(POST)</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 输出一句话，返回给浏览器
        // 1. 通过 resp 获取流 PrintWriter，给浏览器回复数据
        // 2. 为了让浏览器显示中文，需要告诉浏览器编码方式为 utf-8
        /*
        (1) 给回送数据设置编码
        (2) text/html MIME类型，即告诉浏览器返回的数据是 text类型下的html格式数据[MIME类型]
        (3) charset=utf-8 数据编码
        (4) 设置编码格式要在 resp.getWriter() 之前
         */
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>登录成功(GET)</h1>");
        // 为了确保数据返回，可以加 flush()和close()
        // flush() 表示将缓存的数据进行刷新
        writer.flush();
        // close() 表示关闭流，及时释放资源
        writer.close();
    }
}
