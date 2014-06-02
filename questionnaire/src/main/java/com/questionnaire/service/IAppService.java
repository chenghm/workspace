package com.questionnaire.service;

import com.questionnaire.vo.AppBean;

public interface IAppService {

	AppBean register(String username, String password, String age,
			String education, String gender, String income, String occupation,
			String email, String imei);

	AppBean getQuestionnaires(int page, int userId);

	AppBean getQuestionsByQuestionnaire(String questionnaireId);

	AppBean login(String username, String password);

	AppBean saveUserQuestionnaire(int questionnaireId, int userId, String answer);

}
