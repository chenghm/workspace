package com.questionnaire.service.impl;

import com.questionnaire.dao.IBaseDao;
import com.questionnaire.entity.*;
import com.questionnaire.service.IUserQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserQuestionnaireService extends BaseService
        implements IUserQuestionnaireService {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    @Qualifier("baseDao")
    private IBaseDao baseDao;

	/*@SuppressWarnings("unchecked")
    @Override
	public void createUserQuestionnaire(UserQuestionnaire userQuestionnaire) {
		try {
			baseDao.create(userQuestionnaire);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

    @Override
    @Transactional
    public void saveUserQuestionnaire(Questionnaire questionnaire, User user,
                                      String answers) {
        try {

            String[] strs = answers.split(";");
            int questionId;
            Question question;
            UserQuestion userQuestion;
            String answer;
            List<UserQuestion> userQuestions = new ArrayList<UserQuestion>();
            for (String str : strs) {
                questionId = Integer.parseInt(str.split(":")[0]);
                answer = str.split(":")[1];
                userQuestion = new UserQuestion();
                userQuestion.setUser(user);
                question = new Question();
                question.setId(questionId);
                userQuestion.setQuestion(question);
                userQuestion.setAnswer(answer);
                userQuestions.add(userQuestion);
            }
            user.setUserQuestions(userQuestions);
            user.setScore((user.getScore() == null ? 0 : user.getScore())
                    + (questionnaire.getPoint() == null ? 0 : questionnaire
                    .getPoint()));


            UserQuestionnaire userQuestionnaire = new UserQuestionnaire();
            userQuestionnaire.setUser(user);
            userQuestionnaire.setQuestionnaire(questionnaire);
            List<UserQuestionnaire> userQuestionnaires = new ArrayList<UserQuestionnaire>(1);
            userQuestionnaires.add(userQuestionnaire);
            questionnaire.setUserQuestionnaires(userQuestionnaires);

            baseDao.modify(user);
            baseDao.modify(questionnaire);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
