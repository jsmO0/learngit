<%@page import="java.io.IOException"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
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
<%
String author=request.getParameter("author");
String title=request.getParameter("title");
String category=request.getParameter("category");
String content=request.getParameter("content");
String contentPath="C:\\Blog\\"+title+".txt";
File contentFile=new File(contentPath);
Writer writer = null;
if(!contentFile.exists()){
	try{
	contentFile.createNewFile();
	writer = new FileWriter(contentFile);
	writer.write(content);
	writer.flush();
	}catch(IOException e1){
		e1.printStackTrace();
	}catch(Exception e2){
		e2.printStackTrace();
	}finally{
		try{
		writer.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
}
Connection conn=ConnectDatabase.prepareConnect();
Statement stat=null;
PreparedStatement pstat=null;
int checkResult=0;
   try{
	   String sql="insert into blog(title,category,author,contentPath) values(?,?,?,?);";
       pstat=conn.prepareStatement(sql);
       pstat.setString(1, title);
       pstat.setString(2, category);
       pstat.setString(3, author);
       pstat.setString(4, contentPath);
       checkResult=pstat.executeUpdate();
      }catch(SQLException e1){
       e1.printStackTrace();
      }finally{
    	  ConnectDatabase.closeConnect(conn, pstat);
      }
   if(checkResult!=0){
	   request.getRequestDispatcher("successPublish.jsp").forward(request, response);
   }
   %>

</body>
</html>