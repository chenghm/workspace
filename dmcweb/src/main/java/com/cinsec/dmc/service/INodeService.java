package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Node;

public interface INodeService extends IBaseService<Node> {

	Node getNodeById(Integer id);

	Integer createNode(Node node);

	void modifyNode(Node node);

	long getNodesCount();

	List<Node> getNodes(int from, int length);

	long getNodesCount(String groupOp, List<Criterion> criteria);

	List<Node> getNodes(String groupOp, List<Criterion> criteria, int from,
			int length);

	List<Node> findAllNodes();

	List<Node> findByUser(int userId);

	List<Node> findNoUser(int userId);

}
