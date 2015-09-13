package com.zhenghaiqiang.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.zhenghaiqiang.util.JDBC;

public class test {
	public static void main(String[] args) {
		jdbc(0002, "Jerry", "f", 0002);
		jdbc();
	}
	public static void jdbc() {
		Connection conn = JDBC.getConnection();
		String sql = "select * from emp";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, stmt);
		}
	}
	public static void jdbc(int id,String name,String gender,int deptno) {
		Connection conn = JDBC.getConnection();
		String sql = "insert into emp values ("+id+",'"+name+"','"+gender+"',"+deptno+")";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);
			if(i !=0 ){
				System.out.println("≤Â»Î≥…π¶£°");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, stmt);
		}
	}
}
