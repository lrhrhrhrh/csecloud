<%@ page import="com.cse406.cloud.entity.UserEntity" %><%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/24
  Time: 下午6:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    UserEntity user = (UserEntity)session.getAttribute("user");
//    UserEntity user = (UserEntity).getAttribute("user");
    out.print(user.toString());
  %>
  <%--<jsp:forward page="list.jsp"/>--%>

  </body>
</html>
