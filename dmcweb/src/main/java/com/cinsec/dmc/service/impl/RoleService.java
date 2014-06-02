package com.cinsec.dmc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IRoleDao;
import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.exception.SysException;
import com.cinsec.dmc.service.IRoleService;
import com.cinsec.dmc.util.UserContext;

@Service
public class RoleService extends BaseService<Role> implements IRoleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IRoleDao roleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cinsec.dmc.service.impl.IRoleService#getRole(java.lang.Integer)
	 */
	@Override
	public Role getRole(Integer id) {
		return this.getEntity(Role.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cinsec.dmc.service.impl.IRoleService#createRole(com.cinsec.dmc.entity
	 * .Role)
	 */
	@Override
	@Transactional
	public Integer createRole(Role role) throws Exception {
		String currentUser = UserContext.getCurrentUserName();
		Date currentTime = new Date();
		role.setCreatedTime(currentTime);
		role.setCreatedUser(currentUser);

		return roleDao.createRole(role);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cinsec.dmc.service.impl.IRoleService#isDuplicated(com.cinsec.dmc.
	 * entity.Role, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isDuplicated(Role role, String propertyName,
			String propertyValue) {
		List<Role> records = (List<Role>) findEntityByProperty(Role.class,
				propertyName, propertyValue);

		if (CollectionUtils.isEmpty(records)) {
			return false;
		}
		if (role.getId() == null) {
			return true;
		}
		for (Role record : records) {
			if (record.getId() != role.getId()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cinsec.dmc.service.impl.IRoleService#modifyRole(com.cinsec.dmc.entity
	 * .Role)
	 */
	@Override
	@Transactional
	public void modifyRole(Role role) {
		Role r = getRole(role.getId());
		r.setUpdatedTime(new Date());
		r.setUpdatedUser(UserContext.getCurrentUserName());
		r.setCode(role.getCode());
		r.setName(role.getName());
		r.setDescn(role.getDescn());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cinsec.dmc.service.impl.IRoleService#getRolesCount()
	 */
	@Override
	public long getRolesCount() {
		return this.getEntityCount(Role.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cinsec.dmc.service.impl.IRoleService#getRoles(int, int)
	 */
	@Override
	public List<Role> getRoles(int from, int length) {

		return this.getEntityList(Role.class, from, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cinsec.dmc.service.impl.IRoleService#getRolesCount(java.lang.String,
	 * java.util.List)
	 */
	@Override
	public long getRolesCount(String groupOp, List<Criterion> criteria) {
		try {
			return roleDao.getRolesCount(groupOp, criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cinsec.dmc.service.impl.IRoleService#getRoles(java.lang.String,
	 * java.util.List, int, int)
	 */
	@Override
	public List<Role> getRoles(String groupOp, List<Criterion> criteria,
			int from, int length) {
		try {
			return roleDao.getRoles(groupOp, criteria, from, length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
