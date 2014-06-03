package com.questionnaire.service;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;

import java.util.List;

public interface IQuestionnaireService extends IBaseService<Questionnaire> {

    long getQuestionnaireCount();

    List<Questionnaire> getQuestionnaires(int from, int length);

    long getQuestionnaireCount(String groupOp, List<Criterion> criteria);

    List<Questionnaire> getQuestionnaires(String groupOp,
                                          List<Criterion> criteria, int from, int length);

    List<Questionnaire> findAll();

    Questionnaire findById(int id);

    List<Questionnaire> getQuestionnairesByUser(User user, int from,
                                                int length);

}
