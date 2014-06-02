package com.cinsec.dmc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IRoleDao;
import com.cinsec.dmc.entity.Role;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long getRolesCount(String groupOp, List<Criterion> criteria)
			throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(Role.class, criteria);

		String jql = " select count(r) from Role r where " + sql;
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
	public List<Role> getRoles(String groupOp, List<Criterion> criteria,
			int from, int length) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(Role.class, criteria);

		String jql = " from Role u where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			query.setParameter(i + 1, paVals.get(i));
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int createRole(Role role) throws Exception {
		this.create(role);
		return role.getId();
	}

}
