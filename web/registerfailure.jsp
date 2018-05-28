<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/28
  Time: 下午5:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="refresh" content="2; url=/register.jsp">
    <title>Title</title>
</head>
<body>
<% String info = (String) request.getAttribute("info");
    out.print("<h1 align=\"center\">"+info+"</h1>");
%>
</body>
</html>
