package com.akeris.webtodolist.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class DBUtil {
public DBUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
private DataSource dataSource;

public DataSource getDataSource() {
	return dataSource;
}

public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}
public User Login(String username, String password) {
	Connection myConn=null;
	Statement myStmt = null;
	ResultSet myRs= null;
	User usr = null;
	try {
		myConn = dataSource.getConnection();
		myStmt = myConn.createStatement();
		String sql = "select * from users where username =" + username + ", password =" + password + ";";
		myRs = myStmt.executeQuery(sql);
		myRs.last();
		if(myRs.getRow() != 0) {
			int role = Integer.parseInt(myRs.getString("role"));
			usr = new User(username,password,role);
		}
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	finally{
		close(myConn,myStmt,myRs);
	}
	return usr;
}
private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
try{
	if(myStmt!=null)
		myStmt.close();
	if(myRs!=null)
		myRs.close();
	if(myConn!=null)
		myConn.close();
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
}
}
