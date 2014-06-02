package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.RoleUser;
import com.cinsec.dmc.service.IRoleUserService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleUserAction extends BaseAction<RoleUser> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RoleUser> roleUsers;
	private String ajaxResult;
	@Autowired
	private IRoleUserService roleUserService;

	public List<RoleUser> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public String find() {
		roleUserService.writeOperLog("查看角色用户信息");
		return this.refreshGridModel();
	}

	public String save() {
		roleUserService.writeOperLog("保存角色用户信息");
		Map<String, Object> parameters = ActionContext.getContext()
				.getParameters();
		String roleId = ((String[]) (parameters.get("roleId")))[0];
		String userIds = ((String[]) (parameters.get("userIds")))[0];

		try {
			roleUserService.save(Integer.parseInt(roleId), userIds);
			ajaxResult = SUCCESS;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "保存失败!" + e.getMessage();
			return ERROR;
		}

	}

	@Override
	public long getResultSize() {
		int roleId = Integer.parseInt(((String[]) ActionContext
				.getContext().getParameters().get("roleId"))[0]);
		return roleUserService.getRoleUserCountByRoleId(roleId);
	}

	@Override
	public List<RoleUser> listResults(int from, int length) {
		int roleId = Integer.parseInt(((String[]) ActionContext
				.getContext().getParameters().get("roleId"))[0]);
		roleUsers = roleUserService.getRoleUsersByRoleId(roleId, from, length);
		return roleUsers;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoleUser> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRoleUserService(IRoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}

	public String delete() {
		try {
			Map<String, Object> parameters = ActionContext.getContext()
					.getParameters();
			String ids = ((String[]) parameters.get("ids"))[0];
			roleUserService.delete(RoleUser.class, ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除角色失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

}
