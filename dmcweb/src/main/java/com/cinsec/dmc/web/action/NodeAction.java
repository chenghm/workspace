package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Node;
import com.cinsec.dmc.service.INodeService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class NodeAction extends BaseAction<Node> {

	private Node node;
	private List<Node> nodes;

	private static final long serialVersionUID = 1L;
	@Autowired
	private INodeService nodeService;
	private String ajaxResult;

	public String find() {
		nodeService.writeOperLog("查询节点");
		return this.refreshGridModel();
	}
	
	

	public String view() {
		nodeService.writeOperLog("查看节点");
		node = nodeService.getNodeById(node.getId());

		if (node == null) {
			ajaxResult = "节点不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}
	
	
	

	public String create() {
		nodeService.writeOperLog("创建节点");
		try {
			if (!validateData()) {
				ajaxResult = "创建新节点验证失败！";
				return ERROR;
			}
			node.setId(nodeService.createNode(node));
			ajaxResult = SUCCESS;
			// node = null;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "创建新节点失败!" + e.getMessage();
			return ERROR;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean validateData() {
		boolean result = true;
		List<Node> nodes =	(List<Node>) nodeService.findEntityByProperty(Node.class, "number", node.getNumber());

		if (CollectionUtils.isNotEmpty(nodes)) {
			Integer nodeId = node.getId();
			if(nodeId==null || nodeId!=nodes.get(0).getId()){
				messages.put("error_node_number", "节点号已存在！");
				result = false;
			}
		}
		return result;
	}

	public String update() {
		nodeService.writeOperLog("更新节点");
		if (!validateData()) {
			ajaxResult = "更新节点验证失败！";
			return ERROR;
		}
		try {
			nodeService.modifyNode(node);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新节点信息失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除node
	public String delete() {
		nodeService.writeOperLog("删除节点");
		try {
			Map<String,Object> parameters = ActionContext.getContext().getParameters();
			String  ids =( (String[])parameters.get("ids"))[0];
			nodeService.delete(Node.class,  ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除节点失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public long getResultSize() {
		return  nodeService.getNodesCount();
	}

	@Override
	public List<Node> listResults(int from, int length) {
		nodes = nodeService.getNodes(from, length);
		return nodes;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp,List<Criterion> criteria) {
		return (long) nodeService.getNodesCount(groupOp,criteria);
	}

	@Override
	public List<Node> listResults(String groupOp,List<Criterion> criteria, int from, int length) {
		return nodeService.getNodes(groupOp,criteria,from, length);
	}

//	public int getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}


	public List<Node> getNodes() {
		return nodes;
	}


	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	
}
