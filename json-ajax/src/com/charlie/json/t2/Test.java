package com.charlie.json.t2;

import com.charlie.json.t1.A;

public class Test {
    public static void main(String[] args) {
        // A 类的无参构造器是 protected 不再同一包无法调用
//        new A();
        /*
        1. 因为A类和Test类不在同一个包
        2. 在Test包下，就不能访问A类的 protected 方法，包括构造器
        3. A(){} 就是一个匿名内部类，可以理解为A类子类
        4. A(){} 匿名内部类 有一个隐式的无参构造器，根据java基础，无参构造器有默认 super构造器
        5. 当执行 new A(){}; 会调用到A类的无参的 protected 构造器
         */
        A a = new A() {
        };
        // class com.charlie.json.t2.Test$1
        System.out.println(a.getClass());
    }
}
