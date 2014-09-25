package com.cinsec.dmc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Node;
import com.cinsec.dmc.service.INodeService;

@Service
public class NodeService extends BaseService<Node> implements INodeService {

	private static final long serialVersionUID = 1L;

	@Override
	public Node getNodeById(Integer id) {
		return this.getEntity(Node.class, id);
	}

	@Override
	@Transactional
	public Integer createNode(Node node) {
		this.create(node);
		return node.getId();
	}

	@Override
	@Transactional
	public void modifyNode(Node node) {
		this.modify(node);
	}
	
	@Override
	public List<Node> findAllNodes(){
		return this.getEntityList(Node.class);
	}

	@Override
	public long getNodesCount() {
		return this.getEntityCount(Node.class);
	}

	@Override
	public List<Node> getNodes(int from, int length) {
		return this.getEntityList(Node.class, from, length);
	}

	@Override
	public long getNodesCount(String groupOp, List<Criterion> criteria) {
		return this.getResultCount(Node.class, groupOp, criteria);
	}

	@Override
	public List<Node> getNodes(String groupOp, List<Criterion> criteria,
			int from, int length) {
		return this.getResultList(Node.class, groupOp, criteria, from, length);
	}

}
