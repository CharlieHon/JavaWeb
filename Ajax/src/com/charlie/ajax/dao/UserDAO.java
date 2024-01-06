package com.charlie.ajax.dao;

import com.charlie.ajax.entity.User;

/**
 * UserDAO继承自BasicDAO，并指定了User
 * 这时就可以使用BasicDAO中的方法
 */
public class UserDAO extends BasicDAO<User> {
    // ...
}
