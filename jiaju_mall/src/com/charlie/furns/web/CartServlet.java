package com.charlie.furns.web;

import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.CartItem;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BasicServlet {

    // 增加一个属性
    private FurnService furnService = new FurnServiceImpl();

    // 处理Ajax发出的添加商品到购物车的请求
    protected void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        int stock = furn.getStock();
        if (stock == 0) {
            return;
        }
        CartItem cartItem = new CartItem(furn.getId(), furn.getName(), 1, furn.getPrice(), furn.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        // 添加完毕后，返回json数据给前端，前端得到json数据后进行局部刷新即可
        // 1. 规定json格式 {"cartTotalCount": 3}
        // 2. 创建map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("cartTotalCount", cart.getTotalCount());
        // 3. 转成json并返回
        resp.getWriter().write(new Gson().toJson(resultMap));
    }

     //增加一个添加家具数据到购物车的方法
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先得到添加的家具id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 获取到id对应的furn对象
        Furn furn = furnService.queryFurnById(id);

        // 添加家具到购物车，先判断该家具的库存是否为0，如果为0表示缺货，不能再添加到购物车
        int stock = furn.getStock();
        if (stock == 0) {
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }

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
