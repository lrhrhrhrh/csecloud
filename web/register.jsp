<%--
  Created by IntelliJ IDEA.
  User: lrh
  Date: 2018/5/28
  Time: 下午1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign up page</title>
    <style type="text/css">
        h1{text-align:center;}
        h4{text-align:right;color:red;}
        body{background:oldlace}
    </style>

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
                //alert("测试jQuery是否能用");
                $("#form1").submit(function(){
                        var name=$("#name").val();//获取提交的值
                        if(name.length==0){//进行判断，如果获取的值为0那么提示账号不能为空
                                //alert("aa");//测试使用
                                $("#nameError").html("账号不能为空");
                                return false;
                             }
                        //密码进行验证不能为空
                        var password=$("#password").val();//获取提交的密码的值
                        if(password.length==0){
                                $("#passwordError").html("密码不能为空");
                                return false;
                             }
                        //确认密码进行验证
                        var relpassword=$("#relpassword").val();//获取提交的确认密码的值
                        if(relpassword.length==0){
                                $("#relpasswordError").html("确认密码不能为空哦");
                                return false;
                        }

                        if(password!=relpassword){
                                $("#relpasswordError").html("确认密码输入不正确，请重新输入");
                                return false;
                             }
                    });
             });
     </script>
</head>
<body>
<form action="/register" method="post" id="form1">
    <h1>Sign up page</h1>
    <hr>

    <table align="center">
        <tr>
            <td>User name:</td>
            <td><input type="text" name="name" id="name"/>
                <div id="nameError" style="display:inline;color:red;"></div>
            </td>
        </tr>
        <tr>
             <td>Password:</td>
             <td>
                 <input type="password" name="password" id="password">
                 <div id="passwordError" style="display:inline;color:red;"></div>
             </td>
         </tr>
         <tr>
             <td>Comfirm: </td>
             <td>
                 <input type="password" name="relpassword" id="relpassword">
                 <div id="relpasswordError" style="display:inline;color:red;"></div>
             </td>
         </tr>
         <tr>
             <td>email</td>
             <td><input type="text" name="email" id="email"></td>
         </tr>
         <tr>
             <td colspan="1">
             </td>
             <td>
                 <input type="submit" value="Resign up"/>
                 <input type="reset" value="Reset"/>
                 <a href="login.jsp" target="_blank">Resign in</a>
             </td>
                 </tr>
        </tr>
    </table>
</form>

</body>
</html>
