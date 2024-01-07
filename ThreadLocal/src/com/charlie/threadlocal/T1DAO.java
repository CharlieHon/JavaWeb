package com.charlie.threadlocal;

public class T1DAO {
    public void update() {
        // 取出线程关联的threadLocal1对象的数据
        Object o = T1.threadLocal1.get();
        // 获取当前线程名
        String name = Thread.currentThread().getName();
        System.out.println("在T2DAO的update() 线程=" + name
                        + " 取出的dog=" + o);
    }
}
