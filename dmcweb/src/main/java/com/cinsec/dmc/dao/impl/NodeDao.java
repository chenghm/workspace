package com.cinsec.dmc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.INodeDao;
import com.cinsec.dmc.entity.Node;
@Repository
public class NodeDao extends BaseDao<Node> implements INodeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> findByUser(int userId) {
		String jql = "from Node u  left join fetch u.userNodes ru where ru.user.id=:userId";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("userId", userId);
		return (List<Node>) this.getEntityList(jql, parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> findNoUser(int userId) {
		String jql = "from Node u  where not exists ( select 1 from UserNode ru where ru.user.id=:userId and ru.node.id= u.id)";
		Map<String, Object> parameters = new HashMap<String, Object>(1);
		parameters.put("userId", userId);
		return (List<Node>) this.getEntityList(jql, parameters);
	}

}
