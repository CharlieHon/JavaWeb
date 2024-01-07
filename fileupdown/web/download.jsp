<%--
  Created by IntelliJ IDEA.
  User: Charlie
  Date: 2024/1/7
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件下载</title>
    <base href="<%=request.getContextPath()+"/"%>">
</head>
<body>
<h1>文件下载</h1>
<a href="fileDownloadServlet?name=ikun.png">点击下载ikun专属图片</a><br/><br/>
<a href="fileDownloadServlet?name=Java基础.pdf">点击下载 Java.pdf</a><br/><br/>
</body>
</html>
