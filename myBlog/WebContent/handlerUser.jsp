<%@page import="com.ericsson.MySQLconnection.DatabasesHandler"%>
<%@page import="com.ericsson.MySQLconnection.UserInfo"%>
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
</style>
</head>
<body>
<%
DatabasesHandler databasesHandler = new DatabasesHandler();
int totalPage = databasesHandler.getTotalPage();
List<UserInfo> userList = (List<UserInfo>) request.getAttribute("userList");
%>
当前位置：管理用户<br>
<table border="3">
 <tr>
    <th>用户名</th>
    <th>密码</th>
    <th>等级</th>>
    <th>操作</th>   
 </tr>
 <%
    for(UserInfo userInfo : userList){
 %>
 <tr>
     <td><a href="GetBlogByUsernameServlet?username=<%=userInfo.getUsername() %> "><%=userInfo.getUsername() %></a></td>
     <td><%=userInfo.getPassword() %></td>
     <td><%=userInfo.getGrade() %></td>
     <td><a href="DeleteUserServlet?username=<%=userInfo.getUsername() %> ">删除用户</a></td>
 </tr>
 <%
    }
 %>
 <%
 for(int i = 1; i <= totalPage; i++){
 %>
 <a href="UserHandlerServlet?currentPage=<%=i %>">第<%=i %>页</a>&nbsp;
 <%
 }
 %>
 <a href="logout.jsp">退出登录</a>
</body>
</html>