package com.cinsec.dmc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IRoleUserDao;
import com.cinsec.dmc.entity.RoleUser;
import com.cinsec.dmc.service.IRoleUserService;

@Service
public class RoleUserService extends BaseService<RoleUser> implements
		IRoleUserService {
	
	@Autowired
	private IRoleUserDao roleUserDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long getRoleUserCountByRoleId(int roleId) {
		return roleUserDao.getRoleUserCountByRoleId(roleId);
	}

	@Override
	public List<RoleUser> getRoleUsersByRoleId(int roleId, int from, int length) {
		return roleUserDao.getRoleUsersByRoleId(roleId,from,length);
	}


	public void setRoleUserDao(IRoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	@Override
	@Transactional
	public void save(int roleId, String userIds) throws Exception {
		roleUserDao.save(roleId,userIds);
		
	}

	

}
