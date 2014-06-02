package com.cinsec.dmc.dao;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Role;

public interface IRoleDao extends IBaseDao<Role> {

	long getRolesCount(String groupOp, List<Criterion> criteria) throws Exception;

	List<Role> getRoles(String groupOp, List<Criterion> criteria, int from,
			int length) throws Exception;
	
	
	public int createRole(Role role) throws Exception ;

}
