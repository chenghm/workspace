package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Role;

public interface IRoleService extends IBaseService<Role> {

	public abstract Role getRole(Integer id);

	public abstract Integer createRole(Role role) throws Exception;

	public abstract boolean isDuplicated(Role role, String string, String code);

	public abstract void modifyRole(Role role);

	@Override
	public abstract void delete(Class<Role> class1, String ids);

	public abstract long getRolesCount();

	public abstract List<Role> getRoles(int from, int length);

	public abstract long getRolesCount(String groupOp, List<Criterion> criteria);

	public abstract List<Role> getRoles(String groupOp,
			List<Criterion> criteria, int from, int length);

}