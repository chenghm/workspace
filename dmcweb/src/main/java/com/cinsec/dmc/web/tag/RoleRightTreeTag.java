package com.cinsec.dmc.web.tag;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.service.IResourceService;
import com.cinsec.dmc.service.IRoleRightService;
import com.cinsec.dmc.service.IRoleUserService;
import com.cinsec.dmc.util.ContextFactory;
import com.opensymphony.xwork2.util.ValueStack;

public class RoleRightTreeTag extends ComponentTagSupport implements
		Serializable {

	public static final String RIHGT_ROOT = "0";
	public static final String RIHGT_ROOT_P = "-1";
	public static final String RIHGT_ROOT_N = "";
	// public static final String RIHGT_TYPE_M = "0";
	// public static final String RIHGT_TYPE_B = "1";
	// public static final String RIHGT_M_N = "（模块）";
	// public static final String RIHGT_B_N = "（按钮）";

	// private IRoleService roleService;
	private IRoleUserService roleUserService;
	private IResourceService resourceService;
	private IRoleRightService roleRightService;
	private HttpServletRequest request;

	private String imgPath;

	private boolean check;

	private String treeName;

	// private HttpServletResponse response;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922798959661550137L;

	public RoleRightTreeTag() {
		// this.roleService = (IRoleService) ContextFactory
		// .getBean("roleServiceImpl");
		this.roleUserService = (IRoleUserService) ContextFactory
				.getBean("roleUserService");
		this.resourceService = (IResourceService) ContextFactory
				.getBean("resourceService");
		this.roleRightService = (IRoleRightService) ContextFactory
				.getBean("roleRightService");
	}

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		this.request = req;
		// this.response = res;
		return new Component(stack);
	}

	/**
	 * 取标签体
	 * 
	 * @param void
	 * @return String
	 */
	@Override
	protected String getBody() {
		return generateHtmlControl().toString();
	}

	private StringBuffer generateHtmlControl() {

		StringBuffer tree = new StringBuffer();

		this.appendTreePre(tree);

		this.initTree(tree);

		this.addNodesToTree(tree);

		this.appendTreeAft(tree);

		return tree;
	}

	protected void initTree(StringBuffer tree) {
		final String VAR = "var ";
		tree.append(VAR).append(treeName);
		final String DTREE = "=new dTree('";
		tree.append(DTREE).append(treeName);
		tree.append("'").append(",");
		tree.append(check);
		tree.append(",").append("'");
		tree.append(imgPath).append("'");
		tree.append(")").append(";");
		tree.append("\n");

	}

	protected void appendTreePre(StringBuffer tree) {

		final String DIV_BEGIN = "<div id='";
		tree.append(DIV_BEGIN).append(treeName);
		final String CLASS = "' class=\"dtree\">\n";
		tree.append(CLASS);
		tree.append("<script type=\"text/javascript\">").append("\n");

	}

	protected void appendTreeAft(StringBuffer tree) {
		// final String js1 = "$('#right_content').html(";
		String js1 = null;
		if (tree.indexOf("id='dc'") > -1) {
			js1 = "document.getElementById('dc').innerHTML=";
		} else {
			js1 = "document.getElementById('d').innerHTML=";
		}
		// final String js1 = "document.write(";
		tree.append(js1).append(treeName).append("");
		tree.append(";").append("\n");
		final String js2 = "try{setBroTreeV();}catch(e){}\n";
		tree.append(js2);
		tree.append("</script>").append("\n");
		tree.append("</div>").append("\n");
	}

	protected void addNodesToTree(StringBuffer tree) {

		List<Resource> curUserRights = this.getCurUserRightList();
		List<Resource> selRoleRights = this.getSelRoleRights();
		recursiveNodesToTree(tree, curUserRights, selRoleRights);
	}

	private void recursiveNodesToTree(StringBuffer tree,
			List<Resource> curUserRights, List<Resource> selRoleRights) {

		final String js_add = ".add('";
		for (Resource curUserRight : curUserRights) {
			tree.append(treeName).append(js_add);
			tree.append(curUserRight.getId());
			tree.append("'").append(",").append("'");
			tree.append(getPResourceCode(curUserRight));
			tree.append("'").append(",").append("'");
			tree.append(curUserRight.getName());
			if (check) {
				if (isSelRoleRight(curUserRight, selRoleRights)) {
					tree.append("'").append(",").append("'");
					tree.append("checked");
				} else {
					tree.append("'").append(",").append("'");
				}
			}
			tree.append("'").append(")").append(";");
			tree.append("\n");
		}
	}

	private boolean isSelRoleRight(Resource curUserRight,
			List<Resource> selRoleRights) {

		boolean result = false;

		for (Resource selRoleRight : selRoleRights) {
			if (curUserRight.getCode().equals(selRoleRight.getCode())) {
				result = true;
				break;
			}
		}

		return result;
	}

	private String getPResourceCode(Resource curUserRight) {

		String pResourceCode = "";
		if (curUserRight.getCode().equals(curUserRight.getParentCode())) {
			pResourceCode = RIHGT_ROOT;
		} else if ("-1".equals(curUserRight.getParentCode())) {
			pResourceCode = "-1";
		} else {
			pResourceCode = resourceService.getEntityByCode(
					curUserRight.getParentCode()).getId()
					+ "";
		}

		return pResourceCode;
	}

	private void appendTopNode(List<Resource> modules) {

		Resource topNode = new Resource();
		topNode.setId(Integer.valueOf(RIHGT_ROOT));
		topNode.setCode(RIHGT_ROOT);
		Resource topPNode = new Resource();
		topPNode.setId(Integer.valueOf(RIHGT_ROOT_P));
		topPNode.setCode(RIHGT_ROOT_P);
		topNode.setParentCode(topPNode.getCode());
		topNode.setName(RIHGT_ROOT_N);
		modules.add(0, topNode);

	}

	private List<Resource> getCurUserRightList() {

		// User userProfile = UserContext.getCurrentUser();
		// UserProfile userProfile =
		// SysInfoBean.getSysInfoBean().getUserProfile();
		// List<Role> curRoles = SysInfoBean.getSysInfoBean().getRoles();
		// ReturnMessage<Resource> returnMessage = null;
		// if(SysInfoBean.getSysInfoBean().isSuperRole(curRoles)){
		// returnMessage = roleRightService.getRightList(application);
		// }else{
		// List<Role> roles = roleUserService.getRoleListByUser(userProfile);
		// List<Resource> modules = roleRightService.getRightList(roles);
		List<Resource> modules = resourceService.getEntityList(Resource.class);
		// }

		// List<Resource> modules = returnMessage.getReturnObjects();

		this.appendTopNode(modules);

		return modules;
	}

	private List<Resource> getSelRoleRights() {

		Role role = getRole();
		return roleRightService.getRightList(role);

	}

	private Role getRole() {

		Role role = new Role();
		final String key = "roleId";
		String roleId = request.getParameter(key);
		if (StringUtils.isEmpty(roleId)) {
			roleId = "0";
		}
		role.setId(Integer.valueOf(roleId));

		return role;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	// public void setRoleService(IRoleService roleService) {
	// this.roleService = roleService;
	// }

	public void setRoleUserService(IRoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setRoleRightService(IRoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

}
