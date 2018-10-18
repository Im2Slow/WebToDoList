package com.akeris.webtodolist.jdbc;

public class TodoList {
public TodoList(String description) {
		super();
		this.description = description;
	}
public TodoList(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
private int id;
private String description;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
}
