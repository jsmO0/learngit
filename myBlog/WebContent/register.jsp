<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
       background-color: #AEDD81;
    }

</style>
</head>
<body>
请填写注册信息：
<form action="RegisterServlet" method="post" >
 登录用户名:<input type="text" name="registerName"><br>
 登录密码:<input type="password" name="registerPassword"><br>
 再次输入密码:<input type="password" name="doubleCheckPassword"><br>
 用户级别:<select name="grade">
        <option value="admin">管理员</option>
        <option value="user">用户</option>     
         </select><br>
 密保问题:<select name="question">
        <option value="Old">你今年多少岁</option>
        <option value="name">你的完整姓名</option>     
         </select><br>
密保答案:<input type="text" name="answer"><br>
 <input type="submit" value="注册">
</form>
</body>
</html>