package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.BlacklistConfig;

public interface IBlacklistService extends IBaseService<BlacklistConfig> {

	BlacklistConfig getBlacklist(Integer id);

	BlacklistConfig createBlacklist(BlacklistConfig blacklist) throws Exception;

	BlacklistConfig modifyBlacklist(BlacklistConfig blacklist);

	long getBlacklistsCount();

	List<BlacklistConfig> getBlacklist(int from, int length);

	long getBlacklistCount(String groupOp, List<Criterion> criteria) ;

	List<BlacklistConfig> getBlacklists(String groupOp,
			List<Criterion> criteria, int from, int length);

}
