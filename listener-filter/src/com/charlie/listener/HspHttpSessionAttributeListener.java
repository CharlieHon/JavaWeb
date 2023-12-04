package com.charlie.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class HspHttpSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttribute 监听到session添加属性 " +
                httpSessionBindingEvent.getName() + " = " + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttribute 监听到session删除属性 " +
                httpSessionBindingEvent.getName() + " = " + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("HspHttpSessionAttribute 监听到session修改属性 " +
                httpSessionBindingEvent.getName() + " = " + httpSessionBindingEvent.getValue());
    }
}
