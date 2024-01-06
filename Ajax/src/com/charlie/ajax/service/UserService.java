package com.charlie.ajax.service;

import com.charlie.ajax.dao.UserDAO;
import com.charlie.ajax.entity.User;

/**
 * UserService提供业务方法
 */
public class UserService {
    // 属性userDAO，方便操作数据库
    private UserDAO userDAO = new UserDAO();

    public User getUserByName(String name) {
        return userDAO.querySingle("select * from user where username=?", User.class, name);
    }
}
