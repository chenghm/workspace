/*package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.User;
import com.cinsec.dmc.service.IUserService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class CopyOfUserAction extends BaseAction<User> {

	private User user;

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	private String ajaxResult;

	// @Override
	public String find() {
		return this.refreshGridModel();
	}

	public String view() {

		user = userService.getUser(user.getId());

		if (user == null) {
			ajaxResult = "用户不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String create() {

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
		return result;
	}

	public String update() {

		if (!validateData()) {
			ajaxResult = "创建新用户验证失败！";
			return ERROR;
		}
		try {
			userService.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新联系人信息失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除user
	public String delete() {
		try {
			Map<String,Object> parameters = ActionContext.getContext().getParameters();
			String  ids =( (String[])parameters.get("ids"))[0];
			userService.delete(User.class,  ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除联系人失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public int getResultSize() {
		return (int) userService.getUsersCount();
	}

	@Override
	public List<User> listResults(int from, int length) {
		return userService.getUsers(from, length);
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
	public int getResultSize(List<Criterion> criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> listResults(List<Criterion> criteria, int from, int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/