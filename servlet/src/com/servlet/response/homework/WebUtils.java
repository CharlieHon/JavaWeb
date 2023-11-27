package com.servlet.response.homework;

/**
 * 编写一个转换 String -> int 对的方法，处理异常
 */
public class WebUtils {
    public static int parseInt(String money) {
        int m = 0;
        try {
            m = Integer.parseInt(money);
        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
            System.out.println("输入的money格式有误...");
        }
        return m;
    }
}
