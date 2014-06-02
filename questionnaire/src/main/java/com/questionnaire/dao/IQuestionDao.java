package com.questionnaire.dao;

import java.util.List;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Question;

public interface IQuestionDao extends IBaseDao<Question> {

	long getQuestionCount();

	List<Question> getQuestions(int from, int length);

	long getQuestionCount(String groupOp, List<Criterion> criteria);

	List<Question> getQuestions(String groupOp,
			List<Criterion> criteria, int from, int length);

	List<Question> findAll();

	List<Question> getQuestionsByQuestionnaire(int questionnaireId);

}
