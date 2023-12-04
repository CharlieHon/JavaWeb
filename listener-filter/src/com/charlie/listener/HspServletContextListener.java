package com.charlie.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 1. 当一个类实现了接口 ServletContextListener
 * 2. 该类就是一个监听器
 * 3. 该类可以监听的事件，由该类实现的监听接口决定，比如实现 ServletContextListener
 *      该类就可以监听 ServletContext对象的创建和销毁，以此类推
 * 4. HspServletContextListener 就是一个监听者
 * 5. 当web应用启动时，就会产生 ServletContextEvent事件，会调用监听器的对应事件处理方法 contextInitialized
 *      同时会传递事件对象
 * 6. 程序员可以通过 ServletContextEvent事件对象，来获取需要的信息，然后再进行业务处理
 * 7. tomcat如何直到这个监听器的存在？
 *      因为需要在 web.xml 中配置
 */
public class HspServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("HspServletContextListener 监听到 " +
                servletContextEvent.getServletContext() + " 被创建...");
        // 可以获取ServletContext对象，进行业务处理
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("HspServletContextListener 监听到 " +
                servletContextEvent.getServletContext() + " 被销毁...");
        // 可以对servletContext数据进行处理
        System.out.println("进行处理工作...");
    }
}
