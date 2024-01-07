package com.charlie.servlet;

import com.charlie.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FileUploadServlet 被调用...");

        // 1. 判断是不是文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(req)) {
            System.out.println("OK!");
            // 2. 创建 DiskFileItemFactory对象，用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            /**
             * 表单提交的数据就是 input 元素
             *     <input type="file" name="pic" id="" value="" onchange="prev(this)">
             *     家具名：<input type="text" name="name"><br/>
             *     <input type="submit" value="上传">
             */
            // 3. 创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            // 解决到接收到的文件名是中文乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");

            // 4. 关键：servletFileUpload对象 可以把表单提交的数据 text/文件，
            // 将其封装到 FileItem 文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
//                System.out.println("list==>" + list);   // 遍历并分别处理
                /*
                list==>[name=ikun.png, StoreLocation=D:\apache-tomcat-8.0.50\temp\\upload_41fe1bb9_18ce2e483c5__7fd5_00000000.tmp, size=3856bytes, isFormField=false, FieldName=pic,
                name=null, StoreLocation=D:\apache-tomcat-8.0.50\temp\\upload_41fe1bb9_18ce2e483c5__7fd5_00000001.tmp, size=4bytes, isFormField=true, FieldName=name]
                 */
                for (FileItem fileItem : list) {
//                    System.out.println("fileItem=" + fileItem);
                    // 判断是不是一个文件
                    if (fileItem.isFormField()) {   // 如果为true表示文本：text
                        String name = fileItem.getString(); // 得到文本输入框的value
                        System.out.println("家具名=" + name);
                    } else {    // 是一个文件

//                        // 获取上传的文件的名字
//                        String name = fileItem.getName();
//                        // System.out.println("文件名=" + name);
//                        /*
//                         把这个文件上传到服务器的temp下的文件保存到指定目录
//                         1. 指定一个目录，就是我们网站工作目录下
//                         2. 获取完整目录 io/servlet基础
//                         3. fileRealPath目录如下，该目录是和web项目运行环境绑定的，动态
//                         E:\Javaweb\fileupdown\out\artifacts\fileupdown_war_exploded\\upload\
//                         */
//                        String filePath = "/upload/";
//                        String fileRealPath = req.getServletContext().getRealPath(filePath + WebUtils.getYearMonthDay());
//                        // 4. 创建这个上传的目录 => 创建目录(Java基础)
//                        File fileRealPathDirectory = new File(fileRealPath);
//                        if (!fileRealPathDirectory.exists()) {  // 不存在，就创建
//                            fileRealPathDirectory.mkdirs(); // 创建
//                        }
//                        // 5. 将文件拷贝到 fileRealPathDirectory 目录
//                        //  构建了一个上传文件的完整路径：目录+文件名
//                        //  对上传的文件名进行处理，前面增加一个前缀，保证是唯一即可
//                        name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
//                        String fileFullPath = fileRealPathDirectory + "/" + name;
//                        fileItem.write(new File(fileFullPath));

                        WebUtils.uploadFile(req, fileItem);

                        // 6. 提示信息
                        resp.setContentType("text/html;charset=utf-8");
                        resp.getWriter().write("<h1>上传成功!</h1>");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("不是文件表单...");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
