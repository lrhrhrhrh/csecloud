<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/24
  Time: 下午6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>File Upload</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>">--%>
    <%--File: <input type="file" name="upfile"><br/>--%>
    <%--<br/>--%>
    <%--<input type="submit" value="Submit">--%>
<%--</form>--%>
<%--<c:if test="${not empty errorMessage}">--%>
    <%--<input type="text" id="errorMessage" value="${errorMessage}" style="color:red">--%>
<%--</c:if>--%>
<%--</body>--%>
<%--</html>--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>upfile demo</title>
</head>
<body>
<h1>UPFILE INSTANCE</h1>
<form method="post" action="/upload" enctype="multipart/form-data">
    选择一个文件：
    <input type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="Upload"/>

</form>
</body>
</html>
