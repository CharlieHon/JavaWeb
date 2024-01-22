package com.charlie.furns.utils;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;

public class WebUtils {

    // 定义一个文件上传的路径常量
    public static final String FURN_IMG_DIRECTORY = "assets/images/product-image/";

    // 判断请求是否为Ajax请求，根据请求头字段X-Requested-With判断
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static String getYearMonthDay() {
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        return year + "-" + month + "-" + day;
    }

    // 处理更新家具时上传的文件
    public static String uploadFile(HttpServletRequest req, FileItem fileItem) throws Exception {
        String name = fileItem.getName();
        String filePath = WebUtils.FURN_IMG_DIRECTORY;
        String fileRealPath = req.getServletContext().getRealPath(filePath + WebUtils.getYearMonthDay());
        File fileRealPathDirectory = new File(fileRealPath);
        if (!fileRealPathDirectory.exists()) {
            fileRealPathDirectory.mkdirs();
        }
        String fileFullPath = fileRealPathDirectory + "/" + name;
        fileItem.write(new File(fileFullPath));
        return fileFullPath;
    }
}
