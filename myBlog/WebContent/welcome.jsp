<%@page import="com.ericsson.MySQLconnection.ConnectDatabase"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
div{
   text-align: center;
}

</style>
</head>
<body>
欢迎登录：<br>
<%
   
              String registerName=(String)session.getAttribute("registerName");
              String username=(String)session.getAttribute("username");
              if(username!=null){
            	  out.print(username);
              }else if(registerName!=null){
            	  out.print(registerName);
              }
              else{
            	  response.sendRedirect("Login.jsp");
              }
       
            	  
%>
<div>

<br>
<a href="publish.jsp">发布博文</a><br>
<a href="ManageServlet">博文管理</a><br>
<a href="UserHandlerServlet">用户管理</a><br>
<a href="logout.jsp">退出登录</a>
</div>
</body>
</html>