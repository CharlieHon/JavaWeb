package com.charlie.furns.service;

import com.charlie.furns.entity.Member;

public interface MemberService {
    // 注册用户
    public boolean registerMember(Member member);
    // 判断用户是否存在
    public boolean isExistsUsername(String username);

    /**
     * 根据用户传过来的member到数据库中去判断
     * @param member 用户传入的member
     * @return 返回db中的member
     */
    public Member login(Member member);

    // 管理员登录
    public Member admin(Member member);
}
