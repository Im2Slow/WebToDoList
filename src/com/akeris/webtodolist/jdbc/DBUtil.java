package com.akeris.webtodolist.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "select * from users where username ='" + username + "' and password ='" + password + "';";
		myRs = myStmt.executeQuery(sql);
		myRs.last();
		if(myRs.getRow() != 0) {
			int role = myRs.getInt("role");
			usr = new User(username,password,role);
		}
		else {
			throw new Exception(LoginServlet.getConnectionError());
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
public List<TodoList> getToDoList() throws Exception {
	List<TodoList> todolists = new ArrayList<TodoList>();
	Connection myConn=null;
	Statement myStmt=null;
	ResultSet myRs=null;
	try {
		myConn = dataSource.getConnection();
		myStmt = myConn.createStatement();
		String sql = "select * from todos order by id asc";
		myRs = myStmt.executeQuery(sql);
		while(myRs.next()) {
			int id = myRs.getInt("id");
			String description = myRs.getString("description");
			TodoList list = new TodoList(id,description);
			todolists.add(list);
		}
	}
	finally{
		close(myConn,myStmt,myRs);
	}
	return todolists;
}
public void addTodo(TodoList todo) {
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	try {
		myConn = dataSource.getConnection();
		String sql = "INSERT INTO todos(description) VALUES (?)";
		myStmt = myConn.prepareStatement(sql);
		String description = todo.getDescription();
		myStmt.setString(1, description);
		myStmt.execute();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	finally {
		close(myConn,myStmt,myRs);
	}
}
public TodoList fetchTodo(int id) {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	TodoList todo = null;
	try {
		myConn = dataSource.getConnection();
		myStmt = myConn.createStatement();
		String sql = "select * from todos where id="+id;
		myRs = myStmt.executeQuery(sql);
		while(myRs.next()) {
			String description = myRs.getString("description");
			todo = new TodoList(id,description);
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());return null;
	} finally {
		close(myConn,myStmt,myRs);
	}
	return todo;
}
public void updateTodo(TodoList todo) {
	Connection myConn = null;
	PreparedStatement myStmt = null;
	try {
		myConn = dataSource.getConnection();
		String sql = "update todos set description=? where id=?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, todo.getDescription());
		myStmt.setInt(2, todo.getId());
		myStmt.execute();
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}finally {
		close(myConn,myStmt,null);
	}
}
public void deleteTodo(int id) {
	Connection myConn = null;
	Statement myStmt = null;
	try {
		myConn = dataSource.getConnection();
		myStmt = myConn.createStatement();
		String sql = "delete from todos where id="+id;
		myStmt.execute(sql);
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}finally {
		close(myConn,myStmt,null);
	}	
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
