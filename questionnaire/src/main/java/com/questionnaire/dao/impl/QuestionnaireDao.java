package com.questionnaire.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.questionnaire.dao.IQuestionnaireDao;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.entity.User;

@Repository
public class QuestionnaireDao extends BaseDao<Questionnaire> implements
		IQuestionnaireDao {
	private static final long serialVersionUID = 1L;

	@Override
	public long getQuestionnaireCount() {
		return getEntityCount(Questionnaire.class);
	}

	@Override
	public List<Questionnaire> getQuestionnaires(int from, int length) {
		return this.getEntityList(Questionnaire.class, from, length);
	}

	@Override
	public long getQuestionnaireCount(String groupOp, List<Criterion> criteria) {
		long count = 0;
		try {
			String sql = Criterion.convertToSql(groupOp, criteria);
			List<Object> paVals = Criterion.getCriteriaValues(
					Questionnaire.class, criteria);

			String jql = " select count(u) from Questionnaire u where " + sql;
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
	public List<Questionnaire> getQuestionnaires(String groupOp,
			List<Criterion> criteria, int from, int length) {
		List<Questionnaire> list = null;
		try {
			String sql = Criterion.convertToSql(groupOp, criteria);
			List<Object> paVals = Criterion.getCriteriaValues(
					Questionnaire.class, criteria);

			String jql = " from Questionnaire u where " + sql;
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
	public List<Questionnaire> findAll() {
		return this.getEntityList(Questionnaire.class);
	}

	@Override
	public List<Questionnaire> getQuestionnairesByUser(User user, int from,
			int length) {
		StringBuffer jql = new StringBuffer(
				" from Questionnaire q where not exists ( from UserQuestionnaire uq where uq.questionnaire=q and uq.user.id=:uesrid ) and (q.endtime>=:endtime or q.endtime is null)");
		Map<String, Object> parVals = new HashMap<String, Object>();
		parVals.put("uesrid", user.getId());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		parVals.put("endtime", calendar.getTime());
		String age = user.getAge();
		String education = user.getEducation();
		String gender = user.getGender();
		String income = user.getIncome();
		String occupation = user.getOccupation();
		if (StringUtils.isNotEmpty(age)) {
			jql.append(" and (q.age=:age or q.age is null or q.age='')");
			parVals.put("age", age);
		}
		if (StringUtils.isNotEmpty(education)) {
			jql.append(" and (q.education=:education or q.education is null or q.education='')");
			parVals.put("education", education);
		}
		if (StringUtils.isNotEmpty(gender)) {
			jql.append(" and (q.gender=:gender or q.gender is null or q.gender='')");
			parVals.put("gender", gender);
		}
		if (StringUtils.isNotEmpty(income)) {
			jql.append(" and (q.income=:income or q.income is null or q.income='')");
			parVals.put("income", income);
		}
		if (StringUtils.isNotEmpty(occupation)) {
			jql.append(" and (q.occupation=:occupation or q.occupation is null or q.occupation='')");
			parVals.put("occupation", occupation);
		}

		jql.append(" order by id desc");

		return this.getEntityList(jql.toString(), parVals, from, length);
	}

}
