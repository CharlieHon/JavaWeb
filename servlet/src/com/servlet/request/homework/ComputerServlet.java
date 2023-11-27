package com.servlet.request.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ComputerServlet...");
        // 获取浏览器所在电脑的操作系统版本和位数(32/64)
        /*
        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
                    (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36
         (1). Windows NT 10.0; Win64; x64
         (2). Windows NT 10.0 和 x64
         */
        String userAgent = req.getHeader("User-Agent");

        int i = userAgent.indexOf('(');
        int j = userAgent.indexOf(')');
        String osInfos = userAgent.substring(i+1, j);
//        System.out.println("osInfos= " + osInfos);  // Windows NT 10.0; Win64; x64
        String os1 = osInfos.split(";")[0];
        String os2 = osInfos.split(";")[2];
        System.out.println("操作系统：" + os1);  // Windows NT 10.0
        System.out.println("系统位数：" + os2.trim());  //  x64

//        String regStr = "\\((.*)\\)";
//        Pattern compile = Pattern.compile(regStr);
//        Matcher matcher = compile.matcher(userAgent);
//        while (matcher.find()) {
//            String group = matcher.group(0);
//            String group1 = matcher.group(1);
//            System.out.println("group= " + group);
//            System.out.println("group1= " + group1);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
