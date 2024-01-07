package com.charlie.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FileDownServlet被调用...");
        // 1. 先准备要下载的文件[假定这些文件是公共的资源]
        // 重要：保证当tomcat启动后，在工作目录 out下有download文件夹，并且有提供了可供下载的文件
        // 如果没有看到创建的download在工作目录out下 -> rebuild project -> restart，就ok

        // 2. 获取到下载的文件的名字
        req.setCharacterEncoding("utf-8");
        String downloadFileName = req.getParameter("name");
        System.out.println("downloadFileName=" + downloadFileName); // downloadFileName=ikun.png

        // 3. 给http响应，设置响应头 Content-Type，就是文件MIME
        // 通过 servletContext 来获取
        ServletContext servletContext = req.getServletContext();
        // 下载目录从web工程根目录计算 /download/ikun.png
        String downloadPath = "/download/";
        String downloadFullPath = downloadPath + downloadFileName;
        // 根据文件名得到对应的MIME类型
        String mimeType = servletContext.getMimeType(downloadFullPath);
        /*
        ikun.png:       image/png
        Java基础.pdf:    application/pdf
         */
        // System.out.println("mimeType=" + mimeType);
        resp.setContentType(mimeType);

        // 4. 给http响应，设置响应头 Content-Disposition
        // 这里考虑的细节比较多，比如不同的浏览器写法不一样，考虑编码
        // ff 是文件名中文需要 base64 而 ie/chrome 是 URL编码
        /* 解读
        1) 判断发送请求的浏览器类型(火狐/ie/chrome)，如果是Firefox，则中文编码需要 base64
        2) Content-Disposition 是指定下载的数据的展示形式，如果是attachment 则使用文件下载方式
        3) 其它(主流id/chrome) 中文编码使用URL编码
         */
        if (req.getHeader("User-Agent").contains("Firefox")) {
            // 火狐 base64编码
            resp.setHeader("Content-Disposition", "attachment; filename==?UTF-8?B?" +
                    new BASE64Encoder().encode(downloadFileName.getBytes("UTF-8")) + "?=");
        } else {
            // 其它(主流ie/chrome)使用URL编码操作
            resp.setHeader("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode(downloadFileName, "UTF-8"));
        }

        // 5. 读取下载的文件数据，返回给客户端/浏览器
        // 1) 创建一个和要下载的文件关联的输入流
        InputStream resourceAsStream =
                servletContext.getResourceAsStream(downloadFullPath);
        // 2) 得到返回数据的输出流，因为返回的文件大多数是二进制(字节)
        ServletOutputStream outputStream = resp.getOutputStream();
        // 3) 使用工具类，将输入流关联的文件，对拷到输出流，并返回给客户端/浏览器
        IOUtils.copy(resourceAsStream, outputStream);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
