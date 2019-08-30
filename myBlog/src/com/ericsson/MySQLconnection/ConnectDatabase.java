package com.ericsson.MySQLconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
	private static final String DBDRIVER="com.mysql.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/testdb";
	private static final String USER="root";
	private static final String PASSWORD="435991368";
	
	public static Connection prepareConnect() {
		Connection conn=null;
		try{
		Class.forName(DBDRIVER);
		conn=DriverManager.getConnection(DBURL, USER, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}catch( ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnect(Connection conn,Statement stat) {
		try {
		conn.close();
		stat.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void closeConnect(Connection conn,Statement stat,ResultSet res) {
		try {
			res.close();
		    conn.close();
		    stat.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void closeConnect(Connection conn,PreparedStatement pstat,ResultSet res) {
		try {
			res.close();
		    conn.close();
		    pstat.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void closeConnect(Connection conn,PreparedStatement pstat) {
		try {
		    conn.close();
		    pstat.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void insertFromTables(){
		Connection conn=prepareConnect();
		Statement stat=null;
		try {
	         stat=conn.createStatement();
		     String sql="insert into uses values('eee','444')";
		     int res=stat.executeUpdate(sql);
		     System.out.println(res);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
		       closeConnect(conn, stat);
		}
	}
	public static void selectFromTables() {
		Connection conn =prepareConnect();
		Statement stat =null;
		ResultSet res=null;
		
		try {
		stat=conn.createStatement();
		String sql="select *from uses;";
		res=stat.executeQuery(sql);
		while(res.next()) {
			String name=res.getString("username");
			String pass=res.getString("paddword");
			System.out.println(name+": "+pass);
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect(conn, stat, res);
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		
		insertFromTables();
		selectFromTables();
	
		//String sql="insert into uses(username,paddword) values(?,?)";
		/*
		 * PreparedStatement ps=connection.prepareStatement(sql);
		    ps.setString(1, "jsm");
		    ps.setString(2, "lll");
		    ps.executeUpdate();
		    connection.close();
		    ps.close();
        */
	    //Statement stat=connection.createStatement();
	    
	}

}
