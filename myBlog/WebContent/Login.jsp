<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
<style type="text/css">
body{
       background-color: #AEDD81;
    }
div {
        
       position:absolute;
       top:50%;
       left:50%;
       margin:-150px 0 0 -200px;
       width:400px;
       height:300px;
     

     }

</style>
</head>
<body>
<div>
<form  class="center" action="HandlerLoginServlet" method="post">
用户名:<input type="text" name="username"><br>
密码:<input type="password" name="password"> <br>
<input type="submit" value="登录" >
</form><br>
<a class="center" href="register.jsp">注册新账户</a><br>
<a class="center" href="retrievePassword.jsp">找回密码</a>
</div>
</body>
</html>