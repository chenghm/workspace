package com.cinsec.dmc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IRoleUserDao;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleUser;
import com.cinsec.dmc.entity.User;
@Repository
public class RoleUserDao extends BaseDao<RoleUser> implements IRoleUserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long getRoleUserCountByRoleId(int roleId) {

		String jql = "select count(ru) from RoleUser ru where ru.role.id = :roleId";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("roleId", roleId);

		return this.getEntityCount(jql, parameters);
	}

	@Override
	public List<RoleUser> getRoleUsersByRoleId(int roleId, int from, int length) {
		String jql = "from RoleUser ru where ru.role.id = :roleId";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("roleId", roleId);

		return this.getEntityList(jql, parameters, from, length);
	}

	@Override
	public void save(int roleId, String userIds) throws Exception {
		
		String jql1 = " delete from RoleUser ru where ru.role.id=:roleId";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("roleId", roleId);
		this.executeJpl(jql1, parameters);
		
		
		RoleUser roleUser = null;
		User user = null;
		Role role = new Role();
		role.setId(roleId);
		if(StringUtils.isEmpty(userIds)){
			return ;
		}
		for(String userId:userIds.split(",")){
			roleUser = new RoleUser();
			roleUser.setStatus(1);
			user = new User();
			user.setId(Integer.valueOf(userId));
			roleUser.setUser(user);
			roleUser.setRole(role);
			this.create(roleUser);
		}
		
		
	}

}
