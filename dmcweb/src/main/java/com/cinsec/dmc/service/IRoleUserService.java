package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.entity.RoleUser;

public interface IRoleUserService  extends IBaseService<RoleUser>{

	long getRoleUserCountByRoleId(int roleId);

	List<RoleUser> getRoleUsersByRoleId(int roleId, int from, int length);

	void save(int roleId, String userIds) throws Exception;

}
