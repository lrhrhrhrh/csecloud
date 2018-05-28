<%@ page import="com.cse406.cloud.entity.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/28
  Time: 下午5:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="refresh" content="2; url=/list.jsp">
    <title>login page</title>
</head>
<body>
<%
    String info = (String) request.getAttribute("info");
    out.print("<h1 align=\"center\">"+info+"</h1>");
//    UserEntity user = (UserEntity) request.getAttribute("user");
//    out.print(user.toString());
//    request.setAttribute("user", user);
//    request.getRequestDispatcher("/list.jsp").forward(request,response);
%>
</body>
</html>
