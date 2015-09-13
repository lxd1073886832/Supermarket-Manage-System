package com.zhenghaiqiang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhenghaiqiang.dao.QuestionDao;
import com.zhenghaiqiang.entity.Question;
import com.zhenghaiqiang.util.JDBC;

public class QuestionDaoImpl implements QuestionDao {
	//获得所有问题
	@Override
	public List<Question> getAllQuestions() {
		Connection conn = JDBC.getConnection();
		String sql = "select * from question";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Question> questions = new ArrayList<Question>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Question question = new Question();
				question.setQid(rs.getInt("qid"));
				question.setTitle(rs.getString("title"));
				question.setAnswer(rs.getString("answer"));
				question.setScore(rs.getInt("score"));
				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
		return questions;
	}
	//通过id查询问题
	@Override
	public Question getQuestionById(int qid) {
		Connection conn = JDBC.getConnection();
		String sql = "select * from question where qid="+qid;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Question question = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				question = new Question();
				question.setQid(rs.getInt("qid"));
				question.setTitle(rs.getString("title"));
				question.setAnswer(rs.getString("answer"));
				question.setScore(rs.getInt("score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
		return question;
	}
	//添加问题
	@Override
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "insert into question values(?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, question.getQid());
			pstmt.setString(2, question.getTitle());
			pstmt.setString(3, question.getAnswer());
			pstmt.setInt(4, question.getScore());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
	}
	//删除问题
	@Override
	public void deleteQuestionById(int qid) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "delete from question where qid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
	}
	//修改问题
	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub
		Connection conn = JDBC.getConnection();
		String sql = "update question set title=?,answer=? where qid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question.getTitle());
			pstmt.setString(2, question.getAnswer());
			pstmt.setInt(3, question.getQid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
	}
	@Override
	public int getCount() {
		Connection conn = JDBC.getConnection();
		String sql = "select count(*) from question";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.release(conn, rs, pstmt);
		}
		return count;
	}

}
