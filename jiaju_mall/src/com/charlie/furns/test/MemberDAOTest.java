package com.charlie.furns.test;

import com.charlie.furns.dao.MemberDAO;
import com.charlie.furns.dao.impl.MemberDAOImpl;
import com.charlie.furns.entity.Member;
import org.junit.Test;

public class MemberDAOTest {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Test
    public void queryMemberByUsername() {
        Member member = memberDAO.queryMemberByUsername("admin");
        if (member == null) {
            System.out.println("该用户不存在！");
        } else {
            System.out.println("该用户已存在~");
        }
    }

    @Test
    public void saveMember() {
        Member member = new Member(null, "charlie", "charlie", "charlie@tju.edu.cn");
        int update = memberDAO.saveMember(member);
        if (update == -1) {
            System.out.println("添加失败~");
        } else {
            System.out.println("添加用户成功！");
        }
    }

    @Test
    public void queryMemberByUsernameAndPassword() {
        String username = "admin";
        String password = "admin";
        Member member = memberDAO.queryMemberByUsernameAndPassword(username, password);
        if (member != null) {
            System.out.println("登录成功！");
            System.out.println(member);
        } else {
            System.out.println("登录失败~");
        }
    }
}
