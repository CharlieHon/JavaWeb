package com.charlie.furns.web;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.Member;
import com.charlie.furns.entity.Order;
import com.charlie.furns.entity.OrderItem;
import com.charlie.furns.service.OrderService;
import com.charlie.furns.service.impl.OrderServiceImpl;
import com.charlie.furns.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

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

    // 订单管理，显示出用户的所有订单
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int memberId = DataUtils.parseInt(req.getParameter("memberId"), 0);
        List<Order> orders = orderService.showOrders(memberId);
        req.getSession().setAttribute("orders", orders);
        req.getRequestDispatcher("/views/order/order.jsp").forward(req, resp);
    }

    // 订单详情，显示该订单的所有订单项
    protected void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderItemsByOrderId(orderId);
        req.getSession().setAttribute("orderItems", orderItems);
        req.getSession().setAttribute("totalPrice", orderService.totalPrice(orderId));
        req.getSession().setAttribute("totalCount", orderService.totalCount(orderId));
        req.getRequestDispatcher("/views/order/order_detail.jsp").forward(req, resp);
    }
}
