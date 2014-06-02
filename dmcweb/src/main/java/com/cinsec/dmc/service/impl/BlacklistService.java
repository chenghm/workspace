package com.cinsec.dmc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IBlacklistDao;
import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.BlacklistConfig;
import com.cinsec.dmc.exception.SysException;
import com.cinsec.dmc.service.IBlacklistService;
import com.cinsec.dmc.util.UserContext;

@Service
public class BlacklistService extends BaseService<BlacklistConfig> implements
		IBlacklistService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBlacklistDao blacklistDao;

	@Override
	public BlacklistConfig getBlacklist(Integer id) {
		return this.getEntity(BlacklistConfig.class, id);
	}

	@Override
	@Transactional
	public BlacklistConfig createBlacklist(BlacklistConfig blacklist) throws Exception {
		String currentUser = UserContext.getCurrentUserName();
		Date currentTime = new Date();
		blacklist.setCreatedTime(currentTime);
		blacklist.setCreatedUser(currentUser);

		return blacklistDao.createBlacklist(blacklist);
	}

	@Override
	@Transactional
	public BlacklistConfig modifyBlacklist(BlacklistConfig blacklist) {
		BlacklistConfig b = getBlacklist(blacklist.getId());
		b.setUpdatedTime(new Date());
		b.setUpdatedUser(UserContext.getCurrentUserName());
		b.setClassify(blacklist.getClassify());
		b.setName(blacklist.getName());
		b.setStatus(blacklist.getStatus());
		b.setDescn(blacklist.getDescn());
		return b;

	}

	@Override
	public long getBlacklistsCount() {
		return this.getEntityCount(BlacklistConfig.class);
	}

	@Override
	public List<BlacklistConfig> getBlacklist(int from, int length) {
		return this.getEntityList(BlacklistConfig.class, from, length);
	}

	@Override
	public long getBlacklistCount(String groupOp, List<Criterion> criteria) {
		try {
			return blacklistDao.getBlacklistCount(groupOp, criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public List<BlacklistConfig> getBlacklists(String groupOp,
			List<Criterion> criteria, int from, int length) {
		try {
			return blacklistDao.getBlacklists(groupOp, criteria, from, length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

}
