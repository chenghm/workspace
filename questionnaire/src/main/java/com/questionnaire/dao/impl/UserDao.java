package com.questionnaire.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.questionnaire.dao.IUserDao;
import com.questionnaire.entity.User;
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	private static final long serialVersionUID = 1L;

	@Override
	public long getUsersCount() {
		return getEntityCount(User.class);
	}

	@Override
	public List<User> getUsers(int pageNo, int pageSize) {
		return this.getEntityList(User.class, pageNo, pageSize);
	}

	@Override
	public int createUser(User user) throws Exception {
		this.create(user);
		return user.getId();
	}

	@Override
	public long getUsersCount(String groupOp, List<Criterion> criteria)
			throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(User.class, criteria);

		String jql = " select count(u) from User u where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			paVals.get(i).getClass();
			query.setParameter(i + 1, paVals.get(i));
		}
		return (long) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(String groupOp, List<Criterion> criteria,
			int from, int length) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(User.class, criteria);

		String jql = " from User u where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			query.setParameter(i + 1, paVals.get(i));
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUsername(String username) {
		String jql = "from User u where u.username =:username";
		Map<String, Object> parVals = new HashMap<String, Object>(1);
		parVals.put("username", username);
		List<User> users = (List<User>) this.getEntityList(jql, parVals);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		String jql = "from User u where u.username =:username and u.password=:password";
		Map<String, Object> parVals = new HashMap<String, Object>(2);
		parVals.put("username", username);
		parVals.put("password", password);
		List<User> users = (List<User>) this.getEntityList(jql, parVals);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

}
