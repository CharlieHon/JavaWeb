package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerFurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    // 处理首页分页请求
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Page<Furn> page = furnService.page(DataUtils.parseInt(req.getParameter("pageNo"), 1), DataUtils.parseInt(req.getParameter("pageSize"), 4));
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }

    // 处理首页搜索请求
    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        /*
        1. 如果参数有name，但是没有值，则接收的是 ""
        2. 如果name参数都没有，接收到的是 null
         */
        if (name == null) {
            name = "";
        }
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), 4);
        Page<Furn> page = furnService.pageByName(name, pageNo, pageSize);
        req.setAttribute("page", page);

        StringBuilder url =new StringBuilder("customerFurnServlet?action=pageByName");
        if (!"".equals(name)) {
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());

        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }
}
