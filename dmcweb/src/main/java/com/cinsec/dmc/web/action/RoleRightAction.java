package com.cinsec.dmc.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.service.IRoleRightService;
import com.cinsec.dmc.service.IRoleService;
import com.cinsec.dmc.util.UserContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class RoleRightAction extends ActionSupport {

	private static final long serialVersionUID = -1625000595798621463L;
	private static Logger logger = Logger.getLogger(RoleRightAction.class);
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IRoleRightService roleRightService;
	private String roleId;
	private String moduleIds;
	private Map<String, String> messages;
	private String json = "json";
	private String actionStatus = NONE;

	public String query() {
		roleRightService.writeOperLog("查看角色权限信息");
		return SUCCESS;
	}

	public String save() {
		roleRightService.writeOperLog("保存角色权限信息");
		messages = new HashMap<String, String>();
		if (StringUtils.isEmpty(roleId)) {
			messages.put("error_role_right", "角色不能为空！");
			actionStatus = ERROR;
			logger.warn("用户" + UserContext.getCurrentUserName()
					+ "保存授权失败：角色为空！");
			return json;
		}

		try {
			Role role = new Role();
			role.setId(Integer.valueOf(roleId));
			List<Resource> modules = new ArrayList<Resource>();
			if (StringUtils.isNotEmpty(moduleIds)) {
				Resource resource;
				for (String id : moduleIds.split(",")) {
					resource = new Resource();
					resource.setId(Integer.valueOf(id));
					modules.add(resource);
				}
			}

			roleRightService.disRights(role, modules);
			actionStatus = SUCCESS;
			logger.info("用户" + UserContext.getCurrentUserName() + "保存授权成功！");

		} catch (Exception e) {
			actionStatus = ERROR;
			messages.put(
					"error_role_right",
					"用户" + UserContext.getCurrentUserName() + "保存授权失败："
							+ e.getMessage());
			logger.error("用户" + UserContext.getCurrentUserName() + "保存授权失败："
					+ e.getMessage());
		}
		return json;
	}

	@JSON(serialize = false)
	public List<Role> getRoles() {
		return roleService.getEntityList(Role.class);
	}

	@JSON(serialize = false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@JSON(serialize = false)
	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public String getActionStatus() {
		return actionStatus;
	}

}
