package com.cinsec.dmc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IResourceDao;
import com.cinsec.dmc.dao.IRoleRightDao;
import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleResource;
import com.cinsec.dmc.service.IRoleRightService;
import com.cinsec.dmc.util.UserContext;

@Service
public class RoleRightService extends BaseService<RoleResource>
		implements IRoleRightService {

	private static final long serialVersionUID = 9199447210093376921L;

	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IRoleRightDao roleRightDao;

	@Override
	public List<Resource> getRightList(List<Role> roles) {
		Set<Resource> resourceSet = new HashSet<Resource>();
		List<Resource> modules = null;
		for (Role role : roles) {
			modules = resourceDao.getRights(role);
			resourceSet.addAll(modules);
		}

		return new ArrayList<Resource>(resourceSet);
	}

	@Override
	public List<Resource> getRightList(Role role) {
		return resourceDao.getRights(role);
	}

	@Override
	@Transactional
	public void disRights(Role role, List<Resource> modules) {
		roleRightDao.deleteRightsByRole(role);
		//if(CollectionUtils.isNotEmpty(modules)){
			List<RoleResource> roleRightAdds = this.getRoleRightList(role, modules);
			roleRightDao.createList(roleRightAdds);
		//}

	}

	private List<RoleResource> getRoleRightList(Role role, List<Resource> modules) {

		List<RoleResource> roleRights = new ArrayList<RoleResource>();
		RoleResource roleRight = null;
		Date date = new Date();
		String currentUser = UserContext.getCurrentUserName();
		for (Resource module : modules) {
			 roleRight = new RoleResource();
			roleRight.setRole(role);
			roleRight.setResource(module);
			roleRight.setCreatedTime(date);
			roleRight.setCreatedUser(currentUser);
			roleRight.setUpdatedTime(date);
			roleRight.setUpdatedUser(currentUser);
			roleRights.add(roleRight);
		}

		return roleRights;
	}

}
