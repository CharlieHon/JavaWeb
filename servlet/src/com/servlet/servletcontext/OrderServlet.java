package com.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取到ServletContext对象
        ServletContext servletContext = getServletContext();
//        System.out.println("OrderServlet的servletContext:" + servletContext +
//                "\n运行类型:" + servletContext.getClass()); // 运行类型:class org.apache.catalina.core.ApplicationContextFacade

        // 从servletContext获取 visit_count 属性 k-v
        // 判断 visit_count是否为null
//        Object visitCount = servletContext.getAttribute("visit_count");
//        if (visitCount == null) {   // 如果为null,说明是第一次访问
//            servletContext.setAttribute("visit_count", 1);  // "visit_count" - Integer
//            visitCount = 1; // 自动装箱
//        } else {
//            // 去除visitCount属性的值 + 1
//            visitCount = Integer.parseInt(visitCount + "") + 1;
//            servletContext.setAttribute("visit_count", visitCount);
//        }

        Integer visit_count = WebUtils.visitCount(servletContext);

        // 输出提示
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>该网站被访问的次数是" + visit_count + "</h1>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
