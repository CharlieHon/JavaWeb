package com.charlie.furns.web;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.Member;
import com.charlie.furns.entity.Order;
import com.charlie.furns.service.OrderService;
import com.charlie.furns.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class OrderServlet extends BasicServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 业务代码
        Member member = (Member) req.getSession().getAttribute("member");
        if (member == null) {
            // 说明该用户没有登录，将其转发到登录页面
            //resp.sendRedirect(req.getContextPath() + "/views/member/login2.jsp");
            req.getRequestDispatcher("/views/member/login2.jsp").forward(req, resp);
            return; // 直接返回
        }
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart || cart.isEmpty()) {
            //resp.sendRedirect(req.getContextPath() + "/index.jsp");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return; // 直接返回
        }
        // 到此，购物车不为空，且已经登录
        Integer memberId = member.getId();
        String orderId = orderService.saveOrder(cart, memberId);
        // 返回的订单，交给前端显示
        req.getSession().setAttribute("orderId", orderId);
        // 使用重定向方式，请求到 checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/views/order/checkout.jsp");
    }
}
