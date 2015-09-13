package com.zhenghaiqiang.entity;

public class User {
	private int id;
	private String name;
	private String password;
	private boolean start;
	private int grade;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, String password, boolean start,int grade) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.start = start;
		this.grade = grade;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	
}
