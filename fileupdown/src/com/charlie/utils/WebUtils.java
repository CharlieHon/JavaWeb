package com.charlie.utils;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

public class WebUtils {
    public static String getYearMonthDay() {
        // 得到当前的日期
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        String yeaMonthDay = year + "-" + month + "-" + day;
        return yeaMonthDay;
    }

    public static void uploadFile(HttpServletRequest req, FileItem fileItem) throws Exception {
        // 文件名
        String name = fileItem.getName();
        String name1 = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
        String filePath = "/upload/";
        String fileRealPath = req.getServletContext().getRealPath(filePath + WebUtils.getYearMonthDay());
        File fileRealPathDirectory = new File(fileRealPath);
        if (!fileRealPathDirectory.exists()) {
            fileRealPathDirectory.mkdirs();
        }
        String fileFullPath = fileRealPathDirectory + "/" + name1;
        fileItem.write(new File(fileFullPath));
    }

}
