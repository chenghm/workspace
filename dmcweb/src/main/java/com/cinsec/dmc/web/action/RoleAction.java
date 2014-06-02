package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private Role role;
	private List<Role> roles;

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRoleService roleService;
	private String ajaxResult;

	public String find() {
		roleService.writeOperLog("查看角色信息");
		return this.refreshGridModel();
	}

	public String view() {
		roleService.writeOperLog("查看角色信息");
		role = roleService.getRole(role.getId());

		if (role == null) {
			ajaxResult = "用户不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}
	
	
	

	public String create() {
		roleService.writeOperLog("创建角色信息");
		try {
			if (!validateData()) {
				ajaxResult = "创建新用户验证失败！";
				return ERROR;
			}
			role.setId(roleService.createRole(role));
			ajaxResult = SUCCESS;
			// role = null;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "创建新用户失败!" + e.getMessage();
			return ERROR;
		}
	}

	private boolean validateData() {
		boolean result = true;
		String code = role.getCode();
		String name = role.getName();
		String descn = role.getDescn();

		if (StringUtils.isEmpty(code)) {
			messages.put("error_role_code", "角色编码不能为空！");
			result = false;
		} else if (!code.startsWith("ROLE_")) {
			messages.put("error_role_code", "角色编码必须以\"ROLE_\"开头！");
			result = false;
		} else if (roleService.isDuplicated(role, "code", code)) {
			messages.put("error_role_code", "角色编码已存在！");
			result = false;
		} else if (code.length() > 45) {
			messages.put("error_role_code", "角色编码长度不能大于45！");
			result = false;
		}

		if (StringUtils.isEmpty(name)) {
			messages.put("error_role_name", "角色名称不能为空！");
			result = false;

		} else if (roleService.isDuplicated(role, "name", name)) {
			messages.put("error_role_name", "角色名称已存在！");
			result = false;
		} else if (name.length() > 45) {
			messages.put("error_role_name", "角色名称长度不能大于45！");
			result = false;
		}

		if (StringUtils.isNotEmpty(descn) && descn.length() > 200) {
			messages.put("error_role_descn", "角色描述长度不能大于200！");
			result = false;
		}

		return result;
	}

	public String update() {
		roleService.writeOperLog("更新角色信息");
		if (!validateData()) {
			ajaxResult = "创建新用户验证失败！";
			return ERROR;
		}
		try {
			roleService.modifyRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新角色信息失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除role
	public String delete() {
		roleService.writeOperLog("删除角色信息");
		try {
			Map<String, Object> parameters = ActionContext.getContext()
					.getParameters();
			String ids = ((String[]) parameters.get("ids"))[0];
			roleService.delete(Role.class, ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除角色失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public long getResultSize() {
		return roleService.getRolesCount();
	}

	@Override
	public List<Role> listResults(int from, int length) {
		roles = roleService.getRoles(from, length);
		return roles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return (int) roleService.getRolesCount(groupOp, criteria);
	}

	@Override
	public List<Role> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) {
		return roleService.getRoles(groupOp, criteria, from, length);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
