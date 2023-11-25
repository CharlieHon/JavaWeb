package com.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DBServlet extends HttpServlet {
    /***
     * ServletConfig config 使用流程
     * 1. 当DBServlet对象初始化时,Tomcat会同时创建一个 ServletConfig对象
     * 2. 这是如果 DBServlet init() 方法中调用 super.init(config);
     * 3. 会调用父类 GenericServlet
     *     public void init(ServletConfig config) throws ServletException {
     *         this.config = config;    // 把Tomcat创建的ServletConfig对象赋给 GenericServlet的属性 config
     *         this.init();
     *     }
     * 4. 因此如果重写 init() 方法,记得如果在其它方法通过 getServletConfig() 方法获取 ServletConfig
     *      ,则一定记得要调用 super.init(config); 否则,GenericServlet的config为默认值 null
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 在 DBServlet 执行 doGet()/doPost() 时，可以获取到 web.xml 配置的用户名和密码
        // OOP -> 通过现有的方法或对象来搞定
        // DBServlet的父类GenericServlet有 getServletConfig()方法
        /**
         * 1. getServletConfig() 方法是 GenericServlet
         * 2. 返回的 servletConfig 对象是 private transient ServletConfig config;
         * 3. 当一个属性被 transient 修饰,表示该属性不会被串行化(有些重要信息,不希望保存到文件)
         */
        ServletConfig servletConfig = getServletConfig();
        String username = servletConfig.getInitParameter("username");
        String pwd = servletConfig.getInitParameter("pwd");
        System.out.println("初始化参数username= " + username);
        System.out.println("初始化参数pwd= " + pwd);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
