package com.charlie.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 操作顺序分别如下，对应输出如方法内注释
 * 1. 添加属性 name:Bruce
 * 2. 修改属性 name:Leslie
 * 3. 删除属性
 */
public class HspServletContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("HspServletContextAttributeListener 监听到添加属性..." +
                servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());
        // 1. 添加属性 name:Bruce
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("HspServletContextAttributeListener 监听到删除属性..." +
                servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());
        // 2. 修改属性 name:Bruce
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("HspServletContextAttributeListener 监听到修改属性..." +
                servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());
        // 3. 删除属性 name:Leslie
    }
}
