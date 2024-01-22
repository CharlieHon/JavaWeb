package com.charlie.furns.web;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import com.charlie.furns.utils.DataUtils;
import com.charlie.furns.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class FurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    // 使用前面的模板设计模式+反射+动态绑定来调用list方法
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过在地址栏输入 manage/furnServlet?action=list 能够调用该方法
        // System.out.println("FurnServlet 的list方法被调用...");
        List<Furn> furns = furnService.queryFurns();
        // 把furn集合放入到req域
        req.setAttribute("furns", furns);
        // 请求转发
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }

    // 处理添加家具的请求
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取家具信息
        //String name = req.getParameter("name");
        //String maker = req.getParameter("maker");
        //String price = req.getParameter("price");
        //String sales = req.getParameter("sales");
        //String stock = req.getParameter("stock");
        // 图片路径imgPath使用默认即可
        //String imgPath = "assets/images/product-image/16.jpg";

        /*
        后端数据校验
        1. 逐个进行校验
        try {
            BigDecimal price = new BigDecimal(price);
        } catch (Exception e) {
            System.out.println("家具价格格式有误~");
            req.setAttribute("msg", "家具价格格式有误~");
            req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
            return;
        }
        2. 在 new Furn() 处校验一次性验证
        3. SprintMVC有个专门用于数据校验的规则/框架 JSP303 Hibernate Validator
         */

        //Furn furn_ = null;
        //try {
        //    furn = new Furn(null, name, maker, new BigDecimal(price), Integer.parseInt(sales), Integer.parseInt(stock), imgPath);
        //} catch (Exception e) {
        //    System.out.println("添加家具信息有误...");
        //    req.setAttribute("msg", "添加的家具数据有误，请仔细检验~");
        //    req.getRequestDispatcher("/views/manage/furn_add.jsp").forward(req, resp);
        //    return;
        //}

        // 使用BeanUtils完成javabean对象自动封装
        //Furn furn1 = new Furn();
        //try {
        //    // 将 req.getParameterMap() 数据封装到 furn
        //    // 底层使用的是反射，前提是表单提交的数据，字段名name需要和封装的javabean属性名一致
        //    BeanUtils.populate(furn1, req.getParameterMap());
        //} catch (Exception e) {
        //    throw new RuntimeException(e);
        //}

        //=== 使用 DataUtils 封装 JavaBean ===//
        // 1. 将上述自动封装的方法封装到utils中
        // 2. 表单提交的数据，字段名name需要和封装的javabean属性名一致
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());

        // 添加家具
        furnService.addFurn(furn);

        // 请求转发到家具显示页面，即需要重新走一遍furnServlet的list方法
        // 因为这里使用请求转发，当用户刷新页面时会重新发出一次添加请求，就会造成数据重复提交，解决方法->使用请求重定向
        // req.getRequestDispatcher("/manage/furnServlet?action=list").forward(req, resp);

        // 因为重定向实际是让浏览器重新发送请求，所以回送的url是一个完整的url
        //String url = req.getContextPath() + "/manage/furnServlet?action=list";
        // 改为page分页显示
        String url = req.getContextPath() + "/manage/furnServlet?action=page&pageNo=" + req.getParameter("pageNo")
                + "&pageSize=" + req.getParameter("pageSize");
        resp.sendRedirect(url);
        //System.out.println("url=" + url);   // url=/jiaju_mall/manage/furnServlet?action=list
    }

    // 处理删除家具的请求
    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 为了防止接收的id不是一个数字的字符串，使用一个工具类，如果不能转换返回默认值0
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 删除家具
        furnService.deleteFurnById(id);
        // 重定向. 如果使用请求转发方式,刷新页面后重复提交删除信息
        String contextPath = req.getContextPath();
        //String url = contextPath + "/manage/furnServlet?action=list";
        // 改为分页请求
        String url = contextPath + "/manage/furnServlet?action=page&pageNo=" +
                req.getParameter("pageNo") + "&pageSize=" + req.getParameter("pageSize");
        resp.sendRedirect(url);
    }

    // 处理回显家具信息的请求
    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        // 将furn放入到req域中
        req.setAttribute("furn", furn);

        // 1. 将从请求中获取的参数信息pageNo保存到req的域中,则可以在jsp中使用 ${requestScope.pageNo} 获取域数据
        // 2. 如果是请求带来的参数如 pageNo=1,而且通过请求转发到下一个页面,
        //      在下一个页面可以通过 ${param.pageNo} 获取,此时不需要设置域数据,直接请求转发req即可

        // 在jsp中使用 ${param.pageNo},所以不再设置域数据
        //req.setAttribute("pageNo", req.getParameter("pageNo"));
        //req.setAttribute("pageSize", req.getParameter("pageSize"));

        // 请求转发
        req.getRequestDispatcher("/views/manage/furn_update.jsp").forward(req, resp);
    }

    // 处理修改家具信息的请求，增加上传图片功能
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 提交的表单enctype="multipart/form-data"，此时如果前端使用<input name="id" value="">方式提交的数据不能在使用req.getParameter()获取
        // 此时改为将数据直接放在url上，即前端表单 action="furnServlet?action=update&id=${requestScope.furn.id}"
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        if (furn == null) {
            resp.sendRedirect(req.getHeader("Referer"));
            return;
        }
        // 1.判断是不是文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(req)) {
            // 2. 创建 DiskFileItemFactory对象，用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 3. 创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            // 解决接收到文件名是中文乱码的问题
            servletFileUpload.setHeaderEncoding("utf-8");
            // 4. 关键地方，servletFileUpload对象可以把表单提交的数据text/文件，封装到FileItem文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list) {
                    // 先判断是不是一个文件
                    if (fileItem.isFormField()) {  // 如果是true，则不是文件
                        if ("name".equals(fileItem.getFieldName())) {          // 家具名
                            furn.setName(fileItem.getString("utf-8"));
                        } else if ("maker".equals(fileItem.getFieldName())) {   // 制造商
                            furn.setMaker(fileItem.getString("utf-8"));
                        } else if ("price".equals(fileItem.getFieldName())) {   // 价格
                            furn.setPrice(new BigDecimal(fileItem.getString()));
                        } else if ("sales".equals(fileItem.getFieldName())) {   // 销量
                            furn.setSales(new Integer(fileItem.getString()));
                        } else if ("stock".equals(fileItem.getFieldName())) {   // 库存
                            furn.setStock(new Integer(fileItem.getString()));
                        }
                    } else {    // 是文件
                        // 获取文件名
                        String name = fileItem.getName();
                        // 如果用户没有选择新的图片，name=""
                        if (!"".equals(name)) {
                            // 1. 指定一个目录，就是我们网站工作目录下
                            String filePath = WebUtils.FURN_IMG_DIRECTORY + WebUtils.getYearMonthDay();    // 保存到网站的目录
                            // 2. 获取到完整目录
                            String fileRealPath = req.getServletContext().getRealPath(filePath);
                            // 3. 创建这个上传目录
                            File fileRealPathDirectory = new File(fileRealPath);
                            if (!fileRealPathDirectory.exists()) {
                                fileRealPathDirectory.mkdirs();
                            }
                            // 4. 将文件拷贝到fileRealPathDirectory
                            //      给文件名增加前缀，防止文件名重复导致的覆盖问题
                            name = UUID.randomUUID() + "_" + System.currentTimeMillis() + "_" + name;
                            String fileFullPath = fileRealPathDirectory + "/" + name;
                            fileItem.write(new File(fileFullPath));
                            fileItem.getOutputStream().close(); // 关闭流
                            // 上传新图片后，删除旧图
                            String oldImgPath = req.getServletContext().getRealPath(furn.getImgPath());
                            File oldFile = new File(oldImgPath);
                            if (oldFile.isFile() && oldFile.exists()) {
                                oldFile.delete();
                            }
                            // 更新家具的图片路径
                            furn.setImgPath(filePath + "/" + name); // /assets/images/product-image/文件名
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("不是文件表单...");
        }
        // 更新家具->DB
        furnService.updateFurn(furn);
        // 请求转发到update_ok.jsp页面时，如果刷新的话会再次提交修改
        req.getRequestDispatcher("/views/manage/update_ok.jsp").forward(req, resp);
        //resp.sendRedirect(req.getContextPath() + "/views/manage/update_ok.jsp");
    }

    // 处理修改家具信息的请求
    //protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
    //    // 修改家具信息
    //    furnService.updateFurn(furn);
    //    // 重定向
    //    //String url = req.getContextPath() + "/manage/furnServlet?action=list";
    //    // 走分页显示的请求page
    //    String url = req.getContextPath() + "/manage/furnServlet?action=page&pageNo=" +
    //            req.getParameter("pageNo") + "&pageSize=" + req.getParameter("pageSize");
    //    resp.sendRedirect(url);
    //}

    // 处理分页显示请求
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用service方法，获取Page对象
        Page<Furn> page = furnService.page(pageNo, pageSize);
        // 将page放入到req域
        req.setAttribute("page", page);
        // 请求转发到furn_manage.jsp页面
        req.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(req, resp);
    }
}
