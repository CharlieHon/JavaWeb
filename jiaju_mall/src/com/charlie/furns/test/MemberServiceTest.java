package com.charlie.furns.test;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;
import org.junit.Test;

public class MemberServiceTest {
    // 创建一个service对象
    private MemberService memberService = new MemberServiceImpl();

    // 判断用户是否存在
    @Test
    public void isExistsByUsername() {
        String username = "admin";
        if (memberService.isExistsUsername(username)) {
            System.out.println("该用户已存在！");
        } else {
            System.out.println("该用户不存在~");
        }
    }

    // 注册用户
    @Test
    public void registerMember() {
        Member member = new Member(null, "king", "king", "king@qq.com");
        if (memberService.registerMember(member)) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败~");
        }
    }

    // 用户登录
    @Test
    public void login() {
        Member member = new Member(null, "admin", "admin", null);
        System.out.println(memberService.login(member));
    }
}
