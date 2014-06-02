/**
 * Created on 2013年12月19日
 * filename: ResourceDao.java
 * Description: 
 * Copyright: Copyright(c)2013
 * Company: Mars
 */
package com.cinsec.dmc.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IResourceDao;
import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.User;

/**
 * Title: Class XXXX Description:
 * 
 * 
 * @author chenghongming
 * @version xxx
 */
@Repository
public class ResourceDao extends BaseDao<Resource> implements IResourceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.wsn.park.security.dao.IResourceDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findAll() throws SQLException {
		String jql = " from Resource where status=1";

		return (List<Resource>) this.getEntityList(jql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> getRights(Role role) {
		if(role==null){
			return null;
		}
		String jql =" select r.resource from RoleResource r where r.role.id = :roleId";
		Map<String,Object> params = new HashMap<String,Object>(1);
		params.put("roleId", role.getId());
		return (List<Resource>) getEntityList(jql, params);
	}
	
	@Override
	public List<Resource> getUserRights(User user) {
		String jql =" select distinct  rr.resource from RoleResource rr,RoleUser ru  where rr.role.id = ru.role.id and ru.user.id=:userId";
		Map<String,Object> params = new HashMap<String,Object>(1);
		params.put("userId", user.getId());
		return (List<Resource>) getEntityList(jql, params);
	}
	

}
