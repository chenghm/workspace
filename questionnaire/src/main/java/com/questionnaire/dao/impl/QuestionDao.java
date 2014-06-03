package com.questionnaire.dao.impl;

import com.questionnaire.dao.IQuestionDao;
import com.questionnaire.entity.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDao extends BaseDao<Question> implements
        IQuestionDao {
    private static final long serialVersionUID = 1L;

    @Override
    public long getQuestionCount() {
        return getEntityCount(Question.class);
    }

    @Override
    public List<Question> getQuestions(int from, int length) {
        return this.getEntityList(Question.class, from, length);
    }

    @Override
    public long getQuestionCount(String groupOp, List<Criterion> criteria) {
        long count = 0;
        try {
            String sql = Criterion.convertToSql(groupOp, criteria);
            List<Object> paVals = Criterion.getCriteriaValues(
                    Question.class, criteria);

            String jql = " select count(u) from Question u where " + sql;
            Query query = this.getEntityManager().createQuery(jql);
            int size = paVals.size();
            for (int i = 0; i < size; i++) {
                paVals.get(i).getClass();
                query.setParameter(i + 1, paVals.get(i));
            }
            count = (long) query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> getQuestions(String groupOp,
                                       List<Criterion> criteria, int from, int length) {
        List<Question> list = null;
        try {
            String sql = Criterion.convertToSql(groupOp, criteria);
            List<Object> paVals = Criterion.getCriteriaValues(
                    Question.class, criteria);

            String jql = " from Question u where " + sql;
            Query query = this.getEntityManager().createQuery(jql);
            int size = paVals.size();
            for (int i = 0; i < size; i++) {
                query.setParameter(i + 1, paVals.get(i));
            }
            query.setFirstResult(from).setMaxResults(length);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Question> findAll() {
        return this.getEntityList(Question.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> getQuestionsByQuestionnaire(int questionnaireId) {
        String jql = " from Question q where q.questionnaire.id=:questionnaireId";
        Map<String, Object> parVals = new HashMap<String, Object>(1);
        parVals.put("questionnaireId", questionnaireId);
        return (List<Question>) getEntityList(jql, parVals);
    }

}
