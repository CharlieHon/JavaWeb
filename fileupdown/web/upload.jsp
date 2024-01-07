<%--
  Created by IntelliJ IDEA.
  User: Charlie
  Date: 2024/1/7
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--指定base标签 web工程路径/--%>
    <base href="<%=request.getContextPath()+"/"%>">
    <style type="text/css">
        input[type="submit"] {
            outline: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #31B0D5;
            border: none;
            width: 70px;
            height: 35px;
            font-size: 20px;
        }

        img {
            border-radius: 50%;
        }

        form {
            position: relative;
            width: 200px;
            height: 200px;
        }

        input[type="file"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 200px;
            opacity: 0;
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">
        function prev(event) {
            // 获取展示图片的区域
            var img = document.getElementById("preView1");
            // 获取文件对象
            var file = event.files[0];
            // 获取文件阅读器：JS的一个类，直接使用即可
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                // 给img的src设置图片的url
                img.setAttribute("src", this.result);
            }
        }
    </script>
</head>
<body>
<%--表单的enctype属性要设置为 multipart/form-data
    enctype="multipart/form-data";  表示提交的数据是由多个部分组成，有文件和文本
--%>
<form action="fileUploadServlet" method="post" enctype="multipart/form-data">
    家具图1：<img src="" alt="" width="200" height="200" id="preView1">
    <input type="file" name="pic" id="f1" value="" onchange="prev(this)"><br/>
    家具名：<input type="text" name="name"><br/>
    <input type="submit" value="上传">
</form>
</body>
</html>
