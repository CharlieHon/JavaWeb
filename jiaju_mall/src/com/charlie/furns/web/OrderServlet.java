package com.charlie.furns.web;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.Member;
import com.charlie.furns.entity.Order;
import com.charlie.furns.entity.OrderItem;
import com.charlie.furns.service.OrderService;
import com.charlie.furns.service.impl.OrderServiceImpl;
import com.charlie.furns.utils.DataUtils;
import com.charlie.furns.utils.JDBCUtilsByDruid;

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

        /* 分析：
        1. 如果只是希望对orderService.saveOrder方法进行事务控制
        2. 可以不使用过滤器，直接在这里进行提交和回滚即可
        3. 这里做了演示，后面将其提到filter中，控制更多类似的事务管理
         */
        //String orderId = null;
        //try {
        //    orderId = orderService.saveOrder(cart, member.getId());
        //    JDBCUtilsByDruid.commit();      // 提交事务
        //} catch (Exception e) {
        //    JDBCUtilsByDruid.rollback();    // 出现异常，回滚事务
        //    throw new RuntimeException(e);
        //}

        String orderId = orderService.saveOrder(cart, member.getId());
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
