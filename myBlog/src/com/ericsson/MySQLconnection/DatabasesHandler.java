package com.ericsson.MySQLconnection;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MyFilter.MyFileNameFilter;


public class DatabasesHandler {
	
	Connection conn=ConnectDatabase.prepareConnect();
	private Statement stat=null;
	private PreparedStatement pstat=null;
	private ResultSet rs=null;
	private ContentInfo contentInfo=null;
	private UserInfo userInfo = null;
	
	public boolean deleteUser(String username) {
		boolean checkDeleteStatus = false;
    	try {
    		String sql = "delete from users where username=?;";
    		pstat = conn.prepareStatement(sql);
    		pstat.setString(1, username);
    	    int result = pstat.executeUpdate();
    	    if(result>0) {
    	    	checkDeleteStatus = true;
    	    }
    	}catch(SQLException e1) {
    		e1.printStackTrace();
    	}catch(Exception e2) {
    		e2.printStackTrace();
    	}finally {
    		ConnectDatabase.closeConnect(conn, pstat);
    	}  	
    	return checkDeleteStatus;
		
	}
	
	public List<UserInfo> getAllUsers() {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		String sql = "select *from users;";
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setId(rs.getInt("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setQuestion(rs.getString("question"));
				userInfo.setAnswer(rs.getString("answer"));
				userInfo.setGrade(rs.getString("grade"));
				userList.add(userInfo);
				
			}
		} catch (SQLException e1) {	
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}finally {
			ConnectDatabase.closeConnect(conn, stat, rs);
		}
		return userList;
	}
	public int addUser(String registerName,String registerPassword,String question,String answer,
			            String grade ) {
		int result = -1;
		String sql = "insert into users(username,password,question,answer,grade) values(?,?,?,?,?);";
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, registerName);
			pstat.setString(2, registerPassword);
			pstat.setString(3, question);
			pstat.setString(4, answer);
			pstat.setString(5, grade);
			result = pstat.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}finally {
			ConnectDatabase.closeConnect(conn, pstat);
		}
		return result;
		
	}
	
	public String handlerLogin(String username) {
		String password = null;
		String sql="select password,grade from users where username='"
	    		   +username+"';";
		try {	
		stat = conn.createStatement();
		rs=stat.executeQuery(sql);
	       if(rs.next()){
	         password=rs.getString("password");        
	         }
		}catch(SQLException e1) {
			e1.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}finally {
			ConnectDatabase.closeConnect(conn, stat, rs);
		}
		return password;
	}
	
    public List<ContentInfo> getAllContent() {
    	List<ContentInfo> list=new ArrayList<>();
	try{
		String sql="select *from blog;";
		stat=conn.createStatement();
		rs=stat.executeQuery(sql);
		while(rs.next()){
		  contentInfo=new ContentInfo();
		  contentInfo.setId(rs.getInt("id"));
	      contentInfo.setTitle(rs.getString("title"));
	      contentInfo.setCategory(rs.getString("category"));
	      contentInfo.setAuthor(rs.getString("author"));
	      contentInfo.setContentPath(rs.getString("contentPath"));
	      list.add(contentInfo);
		}	
			
	}catch(SQLException e1){
		e1.printStackTrace();
	}catch(Exception e2){
		e2.printStackTrace();
	}finally{
		ConnectDatabase.closeConnect(conn, stat, rs);
	}
	return list;
	 }
    
    public boolean deleteContent(int id) {
    	boolean boo=false;
    	try {
    		String sql="delete from blog where id=?;";
    		pstat=conn.prepareStatement(sql);
    		pstat.setInt(1, id);
    	    int result=pstat.executeUpdate();
    	    if(result>0) {
    	    	boo=true;
    	    }
    	}catch(SQLException e1) {
    		e1.printStackTrace();
    	}catch(Exception e2) {
    		e2.printStackTrace();
    	}finally {
    		ConnectDatabase.closeConnect(conn, pstat);
    	}  	
    	return boo;
    }
    
    public List<File> getAllCommentsPath(String title) {
    	List<File> commentFilePaths = new ArrayList<>();
    	String commentDirPath = "C:\\Blog\\BlogComment";
    	File commentDir = new File(commentDirPath);
    	File[] files = commentDir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().endsWith(title+"Comment.txt")) {
					return true;
				}
				return false;
			}
		});
    	for(File commentFilePath : files) {
    		commentFilePaths.add(commentFilePath);
    	}
    	return commentFilePaths;
    }
    public List<String> readComments(List<File> commentFilePath) {
    	List<String> commentsList = new ArrayList<String>();
    	Reader reader = null;
    	  for(File commentPath:commentFilePath){
    		  String comments = null;
    		   try {
    			   reader = new FileReader(commentPath);
    			   char[] flush = new char[1024];
    	    		int length = 0;
    	    		while(-1 != (length=reader.read(flush))){
    	    		   comments = new String(flush,0,length);
    	    		}
    	    		commentsList.add(comments);
    		   }
    		   catch(FileNotFoundException e1) {
       			e1.printStackTrace();
       		}catch(IOException e2) {
    			   e2.printStackTrace();
    		   }catch(Exception e3) {
    			   e3.printStackTrace();
    		   }finally {
       			try {
        		    reader.close();
        			}catch(IOException e1) {
        				e1.printStackTrace();
        			}catch(Exception e2) {
        				e2.printStackTrace();
        			}
        		}
    	   }
    	  
    	  return commentsList;
    	
    }
    
    public String getFullContent(String title) {
    	String sql="select contentPath from blog where title=?;";
    	String fullContent=null;
    	try {
    	pstat=conn.prepareStatement(sql);
    	pstat.setString(1, title);
    	rs=pstat.executeQuery();
    	if(rs.next()) {
    		Reader reader =null;
    		String contentPath = rs.getString("contentPath");
    		File contentFile=new File(contentPath);
    		try {
    		reader = new FileReader(contentFile);
    		char[] flush =new char[1024];
    		int length=0;
    		while(-1 !=(length=reader.read(flush))){
    		  fullContent=new String(flush,0,length);
    		}
    		
    		}catch(FileNotFoundException e1) {
    			e1.printStackTrace();
    		}catch(IOException e2) {
    			e2.printStackTrace();
    		}finally {
    			try {
    		    reader.close();
    			}catch(IOException e1) {
    				e1.printStackTrace();
    			}catch(Exception e2) {
    				e2.printStackTrace();
    			}
    		}
    	}
    	}catch(SQLException e1) {
    		e1.printStackTrace();
    	}catch(Exception e2) {
    		e2.printStackTrace();
    	}finally {
    		ConnectDatabase.closeConnect(conn, pstat,rs);
    	}
    	
    	return fullContent;
    }
    
    public boolean submitComment(String username,String title,String comments) {
    	boolean checkSubmitStatus = false;
    	int n = 10000;
    	Random random = new Random();
    	int fileNum = random.nextInt(n);
    	String saveCommentPath="C:\\Blog\\BlogComment\\"+fileNum+username+title+"Comment.txt";
    	Writer writer = null;
    	File commentFile = new File(saveCommentPath);
    	if(!commentFile.exists()) {
    		try {
    			commentFile.createNewFile();
                writer =new FileWriter(commentFile);
    	        writer.write(comments);
    	        writer.flush();
    	        checkSubmitStatus = true;
    		}catch(IOException e1) {
    			e1.printStackTrace();
    		}catch(Exception e2) {
    			e2.printStackTrace();
    		}finally {
    			try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		
    	}
    	return checkSubmitStatus;
    	
    }
  
    public boolean edit(String title,String afterEditContent) {
    	boolean checkEdit = false;
    	String contentPath="C:\\Blog\\"+title+".txt";
    	File contentFileOrigin =new File(contentPath);
    	if(contentFileOrigin.exists()) {
    		contentFileOrigin.delete();
    	}
    	Writer writer = null;
    	File contentFile = new File(contentPath);
    	if(!contentFile.exists()) {
    		try {
    			contentFile.createNewFile();
                writer =new FileWriter(contentFile);
    	        writer.write(afterEditContent);
    	        writer.flush();
    	        checkEdit = true;
    		}catch(IOException e1) {
    			e1.printStackTrace();
    		}catch(Exception e2) {
    			e2.printStackTrace();
    		}finally {
    			try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
    	return checkEdit;
    }
         
}

