<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.ericsson.MySQLconnection.ConnectDatabase"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
请输入要找回密码的用户名：<br>
<form action="HandleRetrievePassword.jsp" method="post">
<input type="text" name="username"><br>
设定的密保问题:<select name="question">
        <option value="Old">你今年多少岁</option>
        <option value="name">你的完整姓名</option>     
         </select><br>
设定的密保答案:<input type="text" name="answer"><br>
<input type="submit" value="提交">
</form>

</body>
</html>