<%@ page import="com.cse406.cloud.entity.FileEntity" %>
<%@ page import="com.cse406.cloud.dao.FileDao" %>
<%@ page import="java.util.List" %>
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
    <title>Enter list</title>
</head>
<body>

<div id="container" style="width: 1960px;height: auto">



    <div id="header" style="background-color:#FFA500;">
        <h1 style="margin-bottom:0;">Private Cloud</h1></div>



    <div id="content" style="background-color:#EEEEEE;height:auto;width:1960px;align-content: center;text-align: center">
        <%
            List<FileEntity> fileList = FileDao.list(request.getParameter("directory"));
            if(fileList.size()!=0) {
                String tempDirectory = fileList.get(0).getFileFatherDirectoryForView();
                String fileDirectory = tempDirectory;
                while (!tempDirectory.equals("root")) {
                    FileEntity directoryEntity = FileDao.query(tempDirectory);
                    tempDirectory = directoryEntity.getFileFatherDirectoryForView();
                    fileDirectory = tempDirectory + "/" + fileDirectory;
                }
                out.print("<h2>" + "Current Directory: /" + fileDirectory + "</h2>");
            }

        %>

        <br><br>
        <h2>file list</h2>

        <table  border="" cellspacing="0" cellpadding="2" align="center" style="border-collapse: collapse; align-content: center">
            <tr>
                <th>Filename</th>
                <th>Filesize (KB)</th>
                <th>Operation</th>
            </tr>


            <%

                for(FileEntity file:fileList){
                    if(!file.getFile_ext_name().equals("directory")) {
                        int fileId = file.getId();
                        out.print("<tr><td>" + file.getFileName() + "</td>\n");
                        out.print("<td>" + file.getFileSize() + "</td>\n");
                        out.print("<td><a href=\"/delete?id=" + fileId + "\">Delete</a>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "<a href=\"/download?id=" + fileId + "\">Download</a>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "<a href=\"/movefile.jsp?id=" + fileId + "\">Move</a>"
                                + "</td></tr>\n");
                    }
                }
            %>


        </table>
        <br><br>
        <h2>directory list</h2>
        <table  border="" cellspacing="0" cellpadding="2" align="center" style="border-collapse: collapse; align-content: center">
            <tr>
                <th>Filename</th>
                <th>Operation</th>
            </tr>

            <%
                //                List<FileEntity> fileList2 = FileDao.list("root");
                for(FileEntity file:fileList){
                    if(file.getFile_ext_name().equals("directory")) {
                        int fileId = file.getId();
                        String directoryName = file.getFileName();
                        out.print("<tr><td>" + file.getFileName() + "</td>\n");
                        out.print("<td><a href=\"/delete?id=" + fileId + "\">Delete</a>" + "&nbsp;&nbsp;&nbsp;&nbsp;" +
                                "<a href=\"/movefile.jsp?id=" + fileId + "\">Move</a>"+ "&nbsp;&nbsp;&nbsp;&nbsp;"+
                                "<a href=\"/enterlist.jsp?directory=" + directoryName + "\">Enter</a>"
                                + "</td></tr>\n");
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
