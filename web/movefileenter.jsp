<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/28
  Time: 上午12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
<%@ page import="com.cse406.cloud.entity.FileEntity" %>
<%@ page import="com.cse406.cloud.dao.FileDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cse406.cloud.entity.UserEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/26
  Time: 上午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>Move file</title>
</head>
<body>

<div id="container" style="width: 1960px;height: auto">



    <div id="header" style="background-color:#FFA500;">
        <h1 style="margin-bottom:0;">Private Cloud</h1></div>

    <div id="content1" style="background-color:#EEEEEE; width: 1960px; height: auto ">
        <h2>Create Directory</h2>
        <form method="get" action="/createdirectory" enctype="multipart/form-data">
            <input type="text" name="directory" />
            <input type="hidden" name="idNo" value="<%=request.getParameter("id")%>"/>
            <br/><br/>
            <input type="submit" value="Create"/>
        </form>

    </div>


    <div id="content" style="background-color:#EEEEEE;height:auto;width:1960px;align-content: center;text-align: center">
        <h2>file list</h2>

        <table  border="" cellspacing="0" cellpadding="2" align="center" style="border-collapse: collapse; align-content: center">
            <tr>
                <th>File name</th>
                <th>File size (KB)</th>
                <th>Operation</th>
            </tr>

            <%
                int originID = Integer.parseInt(request.getParameter("originid"));
                int id = Integer.parseInt(request.getParameter("id"));
                FileEntity entity = FileDao.query(id);
                String fatherDirectory = entity.getFileName();
                UserEntity user = (UserEntity)session.getAttribute("user");
                List<FileEntity> fileList = FileDao.list(fatherDirectory, user);

                for(FileEntity file:fileList){
                    if(!file.getFile_ext_name().equals("directory")) {
                        int fileId = file.getId();
                        out.print("<tr><td>" + file.getFileName() + "</td>\n");
                        out.print("<td>" + file.getFileSize() + "</td>\n");
                        out.print("<td><a href=\"/delete?id=" + fileId + "\">Delete</a>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "<a href=\"/download?id=" + fileId + "\">Download</a>"
                                + "</td></tr>\n");
                    }
                }
            %>


        </table>

        <br><br>
        <h2>directory list</h2>

        <table  border="" cellspacing="0" cellpadding="2" align="center" style="border-collapse: collapse; align-content: center">
            <tr>
                <th>Directory name</th>
                <th>Operation</th>
            </tr>

            <%


                for(FileEntity file:fileList){
                    if(file.getFile_ext_name().equals("directory")) {
                        int fileId = file.getId();
                        out.print("<tr><td>" + file.getFileName() + "</td>\n");
                        out.print("<td><a href=\"/delete?id=" + fileId + "\">Delete</a>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "<a href=\"/movefileenter.jsp?originid="+originID+"&id="+fileId+"\">Enter</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+
                                "<a href=\"/movehere?originid="+originID+"&id="+fileId+"\">Move here</a>"+
                                "</td></tr>\n");
                    }
                }
            %>


        </table>


    </div>

    <div id="footer" style="background-color:#FFA500;clear:both;text-align:center;align-items: end">
        CopyRight © CSE406</div>

</div>





</body>
</html>
