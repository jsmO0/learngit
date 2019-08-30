<%@page import="com.ericsson.MySQLconnection.DatabasesHandler"%>
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
String title=request.getParameter("title");
session.setAttribute("title", title);
DatabasesHandler databasesHandler =new DatabasesHandler();
String fullContent = databasesHandler.getFullContent(title);
session.setAttribute("fullContent", fullContent);
%>
博文类容:<br>
<textarea  rows="20" cols="100" ><%=fullContent %></textarea><br>
<form action="CommentServlet" method="post">
    <textarea name="comments" rows="5" cols="100"></textarea><br>
   <input type="submit" value="评论">
</form>


</body>
</html>