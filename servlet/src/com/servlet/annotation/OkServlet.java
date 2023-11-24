package com.servlet.annotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet注解方式快速入门
 * 1. @WebServlet 是一个注解
 * 2. @WebServlet 源码
 * @Target({ElementType.TYPE})
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented  => javadoc生成文档会有记录
 * public @interface WebServlet {
 *     String name() default "";
 *
 *     String[] value() default {};
 *
 *     String[] urlPatterns() default {};
 *
 *     int loadOnStartup() default -1;
 *
 *     WebInitParam[] initParams() default {};
 *
 *     boolean asyncSupported() default false;
 *
 *     String smallIcon() default "";
 *
 *     String largeIcon() default "";
 *
 *     String description() default "";
 *
 *     String displayName() default "";
 * }
 * 3. urlPatterns 对应 web.xml 的 <url-pattern></url-pattern>
 * 4. {"/ok1", "ok2"} 可以给okServlet配置多个 url-pattern
 * 5. 相当于这个注解 @WebServlet(urlPatterns = {"/ok1", "ok2"}) 代替了 web.xml 的配置
 *      底层使用了 反射+注解+IO 来完成一个支撑
 * 6. 浏览器可以这样访问 OkServlet http://localhost:8080/servlet/ok1 或
 *      http://localhost:8080/servlet/ok2
 * 7. 可以根据 @interface WebServlet 源码直到可以配置哪些
 * web.xml init-param 在注解中如何指定？
 *          <init-param>
 *             <param-name></param-name>
 *             <param-value></param-value>
 *         </init-param>
 * initParams = @WebInitParam(name = "xx", value = "yy"))
 * 8. 注解方式开发Servlet和web.xml配置Servlet 流程机制是一样的
 * 9. Servlet urlPattern配置
 *  * 1. 精确匹配
 *  * 配置路径：@WebServlet("ok/zs")
 *  * 访问servlet：localhost:8080/servlet/ok/zs
 *  * 2. 目录匹配
 *  * 配置路径：@WebServlet("ok/*")
 *  * 访问servlet：localhost:8080/servlet/ok/aa   /ok/bb  ok/aa/cc...
 *  * 3. 扩展名匹配
 *  * 配置路径：@WebServlet("*.action") 注意：不允许带 /，否则Tomcat会报错
 *  * 访问servlet：localhost:8080/servlet/zs.action 或 localhost:8080/servlet/ls.action
 *  * 4. 任意匹配
 *  * 配置路径：@WebServlet("/")    @WebServlet("/*")
 *  * 访问servlet：localhost:8080/servlet/aa  /bb     /cc.cpp 任意都可以...
 *  * 提醒： / 和 /* 的配置会匹配所有的请求，比较麻烦，要避免
 */
@WebServlet(urlPatterns = {"/ok1/*", "/ok2", "*.cpp"}) // 记得加上 / ，不然会报错的！
public class OkServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("OkServlet 注解方式 init()...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("注解方式 OkServlet doGet()...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("注解方式 OkServlet doPost()...");
    }
}
