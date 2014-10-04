package com.cinsec.dmc.dao;

import java.util.List;

import com.cinsec.dmc.entity.Node;

public interface INodeDao extends IBaseDao<Node> {

	List<Node> findByUser(int userId);

	List<Node> findNoUser(int userId);

}
