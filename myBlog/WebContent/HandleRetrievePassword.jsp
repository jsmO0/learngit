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
您的登录密码是：
<%
    String Databasepassword=null;
    String Databasequestion=null;
    String Databaseanswer=null;
    String username=request.getParameter("username");
    String question=request.getParameter("question");
    String answer = request.getParameter("answer");
    Connection conn=ConnectDatabase.prepareConnect();
    Statement stat=null;
    ResultSet rs=null;
    String sql="select password,question,answer from users where username='"
 		   +username+"';";
    try{
       stat=conn.createStatement();
       rs=stat.executeQuery(sql);
       while(rs.next()){
    	Databasepassword=rs.getString("password");
    	Databasequestion=rs.getString("question");
    	Databaseanswer=rs.getString("answer");
    	if(question.equals(Databasequestion)&&answer.equals(Databaseanswer)){
    	out.print(Databasepassword);
    	}else{
    		response.sendRedirect("retrievePassword.jsp");
    	}
       }
    }catch(SQLException e1){
    	e1.printStackTrace();
    }finally{
    	ConnectDatabase.closeConnect(conn, stat, rs);
    }
%>

</body>
</html>