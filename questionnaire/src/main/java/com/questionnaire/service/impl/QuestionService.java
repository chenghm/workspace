package com.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionnaire.dao.IQuestionDao;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Question;
import com.questionnaire.service.IQuestionService;
@Service
public class QuestionService extends BaseService<Question> implements IQuestionService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IQuestionDao questionDao;

	@Override
	public long getQuestionCount() {
		return questionDao.getQuestionCount();
	}

	@Override
	public List<Question> getQuestions(int from, int length) {
		return questionDao.getQuestions(from,length);
	}

	@Override
	public long getQuestionCount(String groupOp, List<Criterion> criteria) {
		return questionDao.getQuestionCount(groupOp,criteria);
	}

	@Override
	public List<Question> getQuestions(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return questionDao.getQuestions(groupOp,criteria,from,length);
	}

	@Override
	public List<Question> getQuestionsByQuestionnaire(int questionnaireId) {
		return questionDao.getQuestionsByQuestionnaire(questionnaireId);
	}
}
