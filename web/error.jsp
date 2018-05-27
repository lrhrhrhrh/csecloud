<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/26
  Time: 下午1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>upload fail page</title>
</head>
<body>
<h3>upload fail: </h3>
<c:if test="{not empty errorMessage}">
    <h4 style="color:red">${errorMessage}</h4>

</c:if>
</body>
</html>
