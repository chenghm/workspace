package com.cinsec.dmc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IBlacklistDao;
import com.cinsec.dmc.entity.BlacklistConfig;

@Repository
public class BlacklistDao extends BaseDao<BlacklistConfig> implements IBlacklistDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public BlacklistConfig createBlacklist(BlacklistConfig blacklist) throws Exception {
			this.create(blacklist);
		return blacklist;
	}


	@Override
	public long getBlacklistCount(String groupOp, List<Criterion> criteria) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(BlacklistConfig.class, criteria);

		String jql = " select count(b) from BlacklistConfig b where " + sql;
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
	public List<BlacklistConfig> getBlacklists(String groupOp,
			List<Criterion> criteria, int from, int length) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(BlacklistConfig.class, criteria);

		String jql = " from BlacklistConfig b where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			query.setParameter(i + 1, paVals.get(i));
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

}
