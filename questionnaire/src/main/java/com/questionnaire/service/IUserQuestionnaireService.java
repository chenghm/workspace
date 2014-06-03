package com.questionnaire.service;

import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;

@SuppressWarnings("rawtypes")
public interface IUserQuestionnaireService extends IBaseService {

    void saveUserQuestionnaire(Questionnaire questionnaire, User user,
                               String answers);
}
