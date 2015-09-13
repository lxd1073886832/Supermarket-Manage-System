package com.zhenghaiqiang.dao;

import java.util.List;

import com.zhenghaiqiang.entity.User;

public interface UserDao {
	public abstract List<User> getAllUser();
	public abstract User getUserByNameAndPassword(String name,String password);
	public abstract void addUser(User user);
	public abstract User getUserById(int id);
	public void updateUser(User user);
}
