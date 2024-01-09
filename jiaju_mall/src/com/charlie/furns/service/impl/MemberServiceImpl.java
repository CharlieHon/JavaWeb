package com.charlie.furns.service.impl;

import com.charlie.furns.dao.MemberDAO;
import com.charlie.furns.dao.impl.MemberDAOImpl;
import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;

public class MemberServiceImpl implements MemberService {
    // 定义一个MemberDAO属性
    private MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean registerMember(Member member) {
        // 返回1表示成功，非1值表示失败
        return memberDAO.saveMember(member) == 1;
    }

    /**
     * 判断用户是否存在
     * @param username 用户名
     * @return 如果存在返回true，否则返回false
     */
    @Override
    public boolean isExistsUsername(String username) {
        // 小技巧
        // 如果看某个方法 ctrl+b => 定位到 memberDAO 编译类型的方法
        // ctrl+alt+b => 运行类型的方法
        return memberDAO.queryMemberByUsername(username) != null;
    }

    @Override
    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(),
                member.getPassword());
    }
}
