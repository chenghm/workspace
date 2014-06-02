package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleResource;

public interface IRoleRightService extends IBaseService<RoleResource> {

	List<Resource> getRightList(List<Role> roles);

	List<Resource> getRightList(Role role);

	void disRights(Role role, List<Resource> modules);

}
