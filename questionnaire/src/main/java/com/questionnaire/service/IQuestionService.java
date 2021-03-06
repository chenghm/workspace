package com.questionnaire.service;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Question;

import java.util.List;

public interface IQuestionService extends IBaseService<Question> {

    long getQuestionCount();

    List<Question> getQuestions(int from, int length);

    List<Question> getQuestionsByQuestionnaire(int questionnaireId);

    long getQuestionCount(String groupOp, List<Criterion> criteria);

    List<Question> getQuestions(String groupOp,
                                List<Criterion> criteria, int from, int length);

}
