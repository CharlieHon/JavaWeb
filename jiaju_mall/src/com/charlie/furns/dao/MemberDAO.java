package com.charlie.furns.dao;

import com.charlie.furns.entity.Member;

public interface MemberDAO {    // 需要自己分析，需要哪些方法
    // 提供一个通过用户名返回对应的Member
    public Member queryMemberByUsername(String username);

    // 提供一个保存Member对象到数据库/member表
    public int saveMember(Member member);

    // 根据用户名和密码查询用户的方法
    public Member queryMemberByUsernameAndPassword(String username, String password);

    // 管理员登录
    public Member adminLogin(String username, String password);
}
