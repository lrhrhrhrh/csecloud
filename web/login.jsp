<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/28
  Time: 下午1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <style type="text/css">
        h1{text-align:center;}
        h4{text-align:center;color:red;}
        body{background:cornsilk}
        a{text-decoration:none;font-size:20px;color:black;}
        a:hover{text-decoration:underline;font-size:24px;color:red;}
        </style>
</head>
<body>
<form action="/login" method="post">
    <h1>User login page</h1>
    <hr/>
    <table align="center">
        <tr>
            <td>User name:</td>
            <td><input type="text" name="name" id="name"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr>
            <td colspan="1">

            </td>
            <td>
                <input type="submit" value="Sign in"/>
                <a href="register.jsp" target="_blank">Sign up</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
