package com.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionnaire.dao.IQuestionnaireDao;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;
import com.questionnaire.service.IQuestionnaireService;
@Service
public class QuestionnaireService extends BaseService<Questionnaire> implements IQuestionnaireService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IQuestionnaireDao questionnaireDao;
	
	

	@Override
	public long getQuestionnaireCount() {
		return questionnaireDao.getQuestionnaireCount();
	}

	@Override
	public List<Questionnaire> getQuestionnaires(int from, int length) {
		return questionnaireDao.getQuestionnaires(from,length);
	}

	@Override
	public long getQuestionnaireCount(String groupOp, List<Criterion> criteria) {
		return questionnaireDao.getQuestionnaireCount(groupOp,criteria);
	}

	@Override
	public List<Questionnaire> getQuestionnaires(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return questionnaireDao.getQuestionnaires(groupOp,criteria,from,length);
	}
	@Override
	public List<Questionnaire> findAll(){
		return questionnaireDao.findAll();
	}

	@Override
	public Questionnaire findById(int id) {
		return questionnaireDao.getEntity(id);
	}

	@Override
	public List<Questionnaire> getQuestionnairesByUser(User user, int from,
			int length) {
		
		return questionnaireDao.getQuestionnairesByUser(user,from,length);
	}
}
