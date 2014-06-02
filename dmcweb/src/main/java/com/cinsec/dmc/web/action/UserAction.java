package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.User;
import com.cinsec.dmc.service.IUserService;
import com.cinsec.dmc.util.ContextFactory;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private User user;
//	private int roleId;
	private List<User> users;

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	private String ajaxResult;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;

	public String find() {
		userService.writeOperLog("查询用户");
		return this.refreshGridModel();
	}
	
	public String modifyPassword() {
		userService.writeOperLog("修改密码");
		boolean flag = true;
		if (StringUtils.isEmpty(currentPassword)) {
			messages.put("error_current_password", "当前密码不能为空！");
			flag = false;
		} else if (!userService.validatePassword(currentPassword)) {
			messages.put("error_current_password", "当前密码不正确！");
			flag = false;
		}
		if (StringUtils.isEmpty(newPassword)) {
			messages.put("error_new_password", "新密码不能为空！");
			flag = false;
		} else if (newPassword.length() < 6) {
			messages.put("error_new_password", "新密码长度不能小于6位！");
			flag = false;
		}
		if (StringUtils.isEmpty(confirmPassword)) {
			messages.put("error_confirm_password", "确认密码不能为空！");
			flag = false;
		} else if (!newPassword.equals(confirmPassword)) {
			messages.put("error_confirm_password", "两次密码不一致！");
			flag = false;
		}
		if (!flag) {
			ajaxResult = "更新密码数据验证失败！";
			return ERROR;
		}

		try {
			userService.modifyPassword(newPassword);
			ajaxResult = SUCCESS;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新密码失败!" + e.getMessage();
			return ERROR;
			
	
		}
	} 
	
	public String initModifyProfile(){
		user = userService.getUser(ContextFactory.getUser().getId());
		return "initModifyProfile";
	}

	public String view() {
		userService.writeOperLog("查看用户");
		user = userService.getUser(user.getId());

		if (user == null) {
			ajaxResult = "用户不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}
	
	
	public String findbyrole(){
		userService.writeOperLog("查看用户");
		int roleId = Integer.parseInt(((String[]) ActionContext
				.getContext().getParameters().get("roleId"))[0]);
		users = userService.getUserByRole(roleId);
		
		return SUCCESS;
		
	}
	
	public String findnorole(){
		userService.writeOperLog("查看用户");
		int roleId = Integer.parseInt(((String[]) ActionContext
				.getContext().getParameters().get("roleId"))[0]);
		users = userService.getUserNoRole(roleId);
		
		return SUCCESS;
		
	}

	public String create() {
		userService.writeOperLog("创建用户");
		try {
			if (!validateData()) {
				ajaxResult = "创建新用户验证失败！";
				return ERROR;
			}
			user.setId(userService.createUser(user));
			ajaxResult = SUCCESS;
			// user = null;
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
		String chineseName = user.getChineseName();
		String username = user.getUsername();

		if (StringUtils.isEmpty(chineseName)) {
			messages.put("error_user_chineseName", "中文名不能为空！");
			result = false;
		}

		if (StringUtils.isEmpty(username)) {
			messages.put("error_user_username", "用户名不能为空！");
			result = false;
		} else if (userService.isDuplicated(user, "username", username)) {
			messages.put("error_user_username", "用户名已存在！");
			result = false;
		}
//		if(roleId==0){
//			messages.put("error_user_role", "角色不能为空！");
//			result = false;
//		}
		return result;
	}

	public String update() {
		userService.writeOperLog("更新用户");
		if (!validateData()) {
			ajaxResult = "创建新用户验证失败！";
			return ERROR;
		}
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新用户信息失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除user
	public String delete() {
		userService.writeOperLog("删除用户");
		try {
			Map<String,Object> parameters = ActionContext.getContext().getParameters();
			String  ids =( (String[])parameters.get("ids"))[0];
			userService.delete(User.class,  ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除用户失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public long getResultSize() {
		return  userService.getUsersCount();
	}

	@Override
	public List<User> listResults(int from, int length) {
		users = userService.getUsers(from, length);
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp,List<Criterion> criteria) {
		return (int) userService.getUsersCount(groupOp,criteria);
	}

	@Override
	public List<User> listResults(String groupOp,List<Criterion> criteria, int from, int length) {
		return userService.getUsers(groupOp,criteria,from, length);
	}

//	public int getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
