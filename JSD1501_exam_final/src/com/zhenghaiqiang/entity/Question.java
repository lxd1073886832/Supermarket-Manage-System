package com.zhenghaiqiang.entity;

public class Question {
	private int qid;
	private String title;
	private String answer;
	private int score;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int qid, String title, String answer, int score) {
		super();
		this.qid = qid;
		this.title = title;
		this.answer = answer;
		this.score = score;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
