<%@page import="java.util.List"%>
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
#text{
       background-color: #00CCFF;
}
#comments{
       background-color: #EB7347
       
}

</style>
</head>
<body>
<%
String fullContent = (String)session.getAttribute("fullContent");
List<String> commentsList = (List<String>)request.getAttribute("commentsList");
%>
博文类容:<br>
<textarea id="text" rows="20" cols="100" ><%=fullContent %></textarea><br>
评论：<br>
<%
 for(String comment : commentsList){
%>
<textarea id="comments" rows="5" cols="100"><%=comment %></textarea><br>
<%
}
%>
<a href="ManageServlet">查看其它博文</a>
</body>
</html>