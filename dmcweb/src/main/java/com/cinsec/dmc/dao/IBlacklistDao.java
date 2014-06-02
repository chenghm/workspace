package com.cinsec.dmc.dao;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.BlacklistConfig;

public interface IBlacklistDao extends IBaseDao<BlacklistConfig> {



	long getBlacklistCount(String groupOp, List<Criterion> criteria) throws Exception;


	List<BlacklistConfig> getBlacklists(String groupOp,
			List<Criterion> criteria, int from, int length) throws Exception;


	BlacklistConfig createBlacklist(BlacklistConfig blacklist) throws Exception;

}
