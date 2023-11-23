package com.servlet.annotation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

/**
 * 模拟Tomcat是如何通过 @WebServlet(url-pattern = {"/ok1", "/ok2"})
 * 来装载一个Servlet的
 */
public class TestAnnotationServlet extends HttpServlet {

    private static final HashMap<String, HttpServlet> hm = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 1. 首先要得到扫面的包 路径 io，进而得到类的全路径
        String classAllPath = "com.servlet.annotation.OkServlet";
        // 2. 得到 OkServlet 的 Class对象
        Class<?> cls = Class.forName(classAllPath);
        // 3. 通过Class对象，得到Annotation
        WebServlet annotation = cls.getAnnotation(WebServlet.class);
        System.out.println(annotation);
        String[] strings = annotation.urlPatterns();
        for (String url : strings) {
            System.out.println("url= " + url);
        }
        // 4. 如果匹配url，如果是第一次，tomcat就会创建一个OkServlet实例，放入到HashMap
        HttpServlet instance = (HttpServlet) cls.newInstance();
        System.out.println("instance= " + instance);

        // 简单地模拟
        hm.put("OkServlet", instance);
        System.out.println(hm);
    }
}
