package com.charlie.furns.web;

import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.CartItem;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CartServlet extends BasicServlet {

    // 增加一个属性
    private FurnService furnService = new FurnServiceImpl();

    // 增加一个添加家具数据到购物车的方法
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先得到添加的家具id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 获取到id对应的furn对象
        Furn furn = furnService.queryFurnById(id);

        // TODO: 判断家具是否为空。以下先把正常的逻辑走完，再处理异常情况

        // 根据furn构建cartItem
        CartItem item = new CartItem(furn.getId(), furn.getName(), 1, furn.getPrice(), furn.getPrice());
        // 从session中获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null == cart) { // 说明当前用户的session中没有cart
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        // 将cartItem加入到cart对象
        cart.addItem(item);
        // 添加完毕后，需要返回到 /* 添加家具的页面 */
        // 请求头中的字段 Referer 保存着发送请求的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 更新某个CartItem的数量，即更新购物车
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        int count = DataUtils.parseInt(req.getParameter("count"), 1);
        // 获取session中的购物车cart
        Cart cart =  (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
        }
        // 回到请求更新购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 根据id，删除购物车中的某项家具
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
