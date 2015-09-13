package com.zhenghaiqiang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhenghaiqiang.dao.UserDao;
import com.zhenghaiqiang.entity.User;
import com.zhenghaiqiang.util.JDBC;

public class UserDaoImpl implements UserDao {

	//获得所有用户
	@Override
	public List<User> getAllUser() {
		Connection conn = JDBC.getConnection();
		String sql = "select * from user";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setStart(rs.getBoolean("start"));
					users.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBC.release(conn, rs, pstmt);
			}
		return users;
	}
	//登录验证用户名和密码
	@Override
	public User getUserByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "select * from user where name=? and password=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStart(rs.getBoolean("start"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
		return user;
	}
	//添加用户
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setBoolean(4, user.isStart());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
	}
	//根据id查询用户
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "select * from user where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStart(rs.getBoolean("start"));
				user.setGrade(rs.getInt("grade"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
		return user;
	}
	//修改用户信息
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "update user set name=?,password=?,grade=?,start=? where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			
			pstmt.setInt(3, user.getGrade());
			pstmt.setBoolean(4, user.isStart());
			pstmt.setInt(5, user.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
	}

}
