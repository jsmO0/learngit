<%@page import="java.util.Map"%>
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
<%
  String fullContent = (String)session.getAttribute("fullContent");
%>
<form action="EditServlet" method="post">
          编辑博文：<br>
     <textarea name="afterEditContent" rows="20" cols="100" ><%=fullContent %></textarea><br>
      <input type="submit" value="提交编辑">    
</form><br>
</body>
</html>