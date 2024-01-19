package com.charlie.furns.web;

import com.charlie.furns.entity.Member;
import com.charlie.furns.service.MemberService;
import com.charlie.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 该servlet处理和Member相关的请求
 */
public class MemberServlet2 extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    // 处理会员注册
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("RegisterServlet 被调用...");
        // 接收用户注册信息->一定要看前端代码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // 获取用户提交的验证码
        String code = req.getParameter("code");
        // 从session中获取到KaptchaServlet创建的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 立即删除session中的验证码，防止该验证码被重复使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 如果token不为null，并且和用户提交的验证码一致，就继续
        if (token != null && token.equalsIgnoreCase(code)) {
            // 判断用户名是否可用
            if (!memberService.isExistsUsername(username)) {
                // 用户名不存在-注册
                Member member = new Member(null, username, password, email);
                if (memberService.registerMember(member)) { // 注册成功
                    // 请求转发
                    req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req, resp);
                } else {    // 注册失败
                    req.getRequestDispatcher("/views/member/register_fail.jsp").forward(req, resp);
                }
            } else {
                // 用户名不可用
                req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
            }
        } else {    // 验证码不正确
            // 待会一个信息，要求显示到注册选项页
            req.setAttribute("active", "register");
            req.setAttribute("msg", "验证码有误~");
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        }
    }

    // 处理会员登录
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如果在登录界面，用户没有输入内容就直接提交，后台接收到的就是空串 ""
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Member member = new Member(null, username, password, null);
        // 在这里对member重新赋值了，这样id和email就是从db中拿出来的
        if ((member = memberService.login(member)) == null) {
            // 把登录错误信息，放入到request域
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 页面转发-登录失败
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
        } else {
            // 将得到的member对象放入到session
            req.getSession().setAttribute("member", member);
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req, resp);
        }
    }

    // 注销登录
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁当前用户的session
        req.getSession().invalidate();
        // 重定向到网站首页->刷新首页
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
