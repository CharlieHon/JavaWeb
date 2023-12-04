<%--
  Created by IntelliJ IDEA.
  User: HX
  Date: 2023/12/4
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>电影评论</title>
</head>
<body>
<h1><%=request.getParameter("username")%>发表的评论是</h1><br/>
评论内容：<%=request.getParameter("comment")%>
</body>
</html>
