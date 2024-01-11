package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends BasicServlet{

    private MemberService memberService = new MemberServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        if (memberService.admin(member) != null) {  // 登录成功
            req.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(req, resp);
        } else {    // 登录失败
            req.setAttribute("msg", "登录失败！用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/manage/manage_login.jsp").forward(req, resp);
        }
    }
}
