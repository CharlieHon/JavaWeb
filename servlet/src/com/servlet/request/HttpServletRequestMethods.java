package com.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletRequestMethods extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 使用 req 对象，获取表单提交的各种数据
        System.out.println("HttpServletRequestMethods doGet()...");

        // 获取和http请求头相关信息
        System.out.println("URI= " + req.getRequestURI());  // /servlet/requestMethods
        System.out.println("URL= " + req.getRequestURL());  // http://localhost:8080/servlet/requestMethods
        System.out.println("请求的客户端ip地址= " + req.getRemoteAddr());   // 127.0.0.1
        // 思考题：如果发现某个ip在10s内，访问的次数超过100此，就封禁ip
        /*
        1. 用一个集合 concurrentHashmap[ip：访问次数] 2. [线程/定时扫描] 3.做处理
         */
        System.out.println("http请求头Host= " + req.getHeader("Host"));    // localhost:8080
        // 说明：如果希望得到请求头的相关信息，可以使用 req.getHeader("请求头字段")
        System.out.println("请求的发起地址= " + req.getHeader("Referer"));     // http://localhost:8080/servlet/register_request.html
        System.out.println("http请求方式= " + req.getMethod()); // GET
        // 得到访问网站的浏览器类型
        String userAgent = req.getHeader("User-Agent");
        String[] s = userAgent.split(" ");
        System.out.println("访问网站的浏览器= " + s[s.length-1].split("\\/")[0]);   // Safari
        // 获取JSESSIONID的值
        // Cookie: Idea-8296f2b3=5bf8bc6d-43c8-4cb1-bb34-d1c0686c62cd
        String cookie = req.getHeader("Cookie");
        String JSESSIONID = cookie.split("=")[1];
        System.out.println("JSESSIONID= " + JSESSIONID);

        // 获取和请求参数相关信息，注意要求在返回数据前，获取参数
        // 解决接收参数的中文乱码问题，写在接收参数之前。浏览器默认编码为 url-encoding
        req.setCharacterEncoding("utf-8");
        // 1. 获取表单的数据[单个数据]
        // username=TOM&hobby=hsp&hobby=lh
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");

        // 2. 获取表单的一组数据
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println("username= " + username);
        System.out.println("pwd= " + pwd);
        // 增强for循环的快捷键 iter -> 回车即可
        for (String hobby : hobbies) {
            System.out.println("hobby= " + hobby);
        }

        // 返回接收到的信息，给浏览器显示。
        // 本质就是在 http响应头，加上 Context-Type: text/html;charset=utf-8
        // text/html：表示返回数据的类型，浏览器会根据这个类型来解析数据
        // text/plain：表示返回的数据，让浏览器以纯文本类型进行解析
        // application/x-tar：表示返回的是文件，浏览器就会以下载文件的方式进行处理
        resp.setContentType("text/plain;charset=utf-8"); // 设置返回信息的编码方式
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>你好，世界</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
