<%@page import="com.ericsson.MySQLconnection.ContentInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.ericsson.MySQLconnection.ConnectDatabase"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
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
</style>
</head>
<body>
<p>当前位置：博文管理</p>

<%
  List<ContentInfo> list = (List<ContentInfo>)request.getAttribute("content");
%>
<table border="3">
 <tr>
    <th>标题</th>
    <th>类别</th>
    <th>作者</th>
    <th>操作</th>
 </tr>
 <%
   for(ContentInfo conInfo:list){
 %>
 <tr>
   <td><a href="FullContentServlet?title=<%=conInfo.getTitle()%>" > <%=conInfo.getTitle() %> </a></td>
    <td><%=conInfo.getCategory() %></td>
     <td><%=conInfo.getAuthor() %></td>
     <td><a href="DeleteServlet?id=<%=conInfo.getId() %> ">删除</a> <a href="comment.jsp?title=<%=conInfo.getTitle()%>">评论</a></td>
 </tr>   
 <%
 }
 %>
</table>
</body>
</html>