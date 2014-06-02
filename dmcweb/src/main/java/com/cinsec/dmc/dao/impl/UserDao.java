/**
 * Created on 2013年12月19日
 * filename: UserDao.java
 * Description: 
 * Copyright: Copyright(c)2013
 * Company: Mars
 */
package com.cinsec.dmc.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IUserDao;
import com.cinsec.dmc.entity.User;

/**
 * Title: Class XXXX Description:
 * 
 * 
 * @author chenghongming
 * @version xxx
 */
@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserDao extends BaseDao implements IUserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.wsn.park.security.dao.IUserDao#findUserByUsername(java.lang.String)
	 */
	@Override
	public User findUserByUsername(String username) throws SQLException {
		String jql = "from User u where u.username =:username";
		Map<String, Object> parVals = new HashMap<String, Object>(1);
		parVals.put("username", username);
		List<User> users = this.getEntityList(jql, parVals);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.wsn.park.security.dao.IUserDao#getAuthorities(cn.wsn.park.base.entity
	 * .PlatformUser)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(User user)
			throws SQLException {
		String jql = "select ru.role.code from RoleUser ru where ru.user.username= :username";
		Map<String, Object> parVals = new HashMap<String, Object>(1);
		parVals.put("username", user.getUsername());
		List<String> roleCodeList = this.getEntityList(jql, parVals);
		Collection<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		for (String roleCode : roleCodeList) {
			authorities.add(new SimpleGrantedAuthority(roleCode));
		}
		return authorities;
	}

	@Override
	public long getUsersCount() {
		return getEntityCount(User.class);
	}

	@Override
	public List<User> getUsers(int from, int length) {
		return this.getEntityList(User.class, from, length);
	}

	@Override
	public int createUser(User user) throws Exception {
		this.create(user);
		return user.getId();
		// return 0;
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

	@Override
	public List<User> getUserByRole(int roleId) {
		String jql = "from User u  left join fetch u.roleUsers ru where ru.role.id=:roleId";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("roleId", roleId);
		return this.getEntityList(jql, parameters);
	}

	@Override
	public List<User> getUserNoRole(int roleId) {
		String jql = "from User u  where not exists ( select 1 from RoleUser ru where ru.role.id=:roleId and ru.user.id= u.id)";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("roleId", roleId);
		return this.getEntityList(jql, parameters);
	}

}
