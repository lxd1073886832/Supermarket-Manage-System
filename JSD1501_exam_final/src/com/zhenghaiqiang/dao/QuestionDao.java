package com.zhenghaiqiang.dao;

import java.util.List;

import com.zhenghaiqiang.entity.Question;

public interface QuestionDao {
	public abstract List<Question> getAllQuestions();
	public abstract Question getQuestionById(int qid);
	public abstract void addQuestion(Question question);
	public abstract void deleteQuestionById(int qid);
	public abstract void updateQuestion(Question question);
	public abstract int getCount();
}
