package com.cinsec.dmc.dao;

import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleResource;

public interface IRoleRightDao extends IBaseDao<RoleResource> {

	void deleteRightsByRole(Role role);

//	void createList(List<RoleResource> roleRightAdds);

}
