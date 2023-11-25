package com.servlet.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        System.out.println("PayServlet的servletContext:" + servletContext +
//                ",运行类型:" + servletContext.getClass());

//        Object visitCount = servletContext.getAttribute("visit_count");
//        if (visitCount == null) {
//            servletContext.setAttribute("visit_count", 1);
//            visitCount = 1;
//        } else {
//            visitCount = Integer.parseInt(visitCount + "") + 1;
//            servletContext.setAttribute("visit_count", visitCount);
//        }

        // 获取到ServletContext对象
        ServletContext servletContext = getServletContext();
        Integer visit_count = WebUtils.visitCount(servletContext);

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
