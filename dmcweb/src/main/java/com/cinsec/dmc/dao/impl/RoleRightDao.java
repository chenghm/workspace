package com.cinsec.dmc.dao.impl;

import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IRoleRightDao;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleResource;

@Repository
public class RoleRightDao extends BaseDao<RoleResource> implements IRoleRightDao{

	private static final long serialVersionUID = -6070765485224279796L;

	@Override
	public void deleteRightsByRole(Role role)  {

		StringBuffer jql = new StringBuffer();
		final String jql1 = "DELETE FROM ";
		jql.append(jql1).append(RoleResource.class.getSimpleName());
		final String jql2 = " WHERE role.id=";
		jql.append(jql2).append(role.getId());
		
		this.executeJpl(jql.toString());
	}

}
