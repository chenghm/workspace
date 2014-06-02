package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.BlacklistConfig;
import com.cinsec.dmc.service.IBlacklistService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class BlacklistAction extends BaseAction<BlacklistConfig> {

	private BlacklistConfig blacklist;
	private List<BlacklistConfig> blacklists;

	private static final long serialVersionUID = 1L;
	@Autowired
	private IBlacklistService blacklistService;
	private String ajaxResult;

	public String find() {
		blacklistService.writeOperLog("查看黑名单信息");
		return this.refreshGridModel();
	}

	public String view() {
		blacklistService.writeOperLog("查看黑名单信息");
		blacklist = blacklistService.getBlacklist(blacklist.getId());

		if (blacklist == null) {
			ajaxResult = "黑名单不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String create() {
		blacklistService.writeOperLog("创建黑名单信息");
		try {
			if (!validateData()) {
				ajaxResult = "创建新黑名单验证失败！";
				return ERROR;
			}
			blacklist =  blacklistService.createBlacklist(blacklist);
			ajaxResult = SUCCESS;
			// blacklist = null;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "创建黑名单失败!" + e.getMessage();
			return ERROR;
		}
	}

	private boolean validateData() {
		boolean result = true;
		String classify = blacklist.getClassify();
		String name = blacklist.getName();

		if (StringUtils.isEmpty(classify)) {
			messages.put("error_blacklist_classify", "黑名单类型不能为空！");
			result = false;
		}

		if (StringUtils.isEmpty(name)) {
			messages.put("error_blacklist_name", "黑名单不能为空！");
			result = false;
		}
		return result;
	}

	public String update() {
		blacklistService.writeOperLog("更新黑名单信息");
		if (!validateData()) {
			ajaxResult = "更新黑名单验证失败！";
			return ERROR;
		}
		try {
			blacklist = 	blacklistService.modifyBlacklist(blacklist);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新黑名单信息失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除blacklist
	public String delete() {
		blacklistService.writeOperLog("删除黑名单信息");
		try {
			Map<String, Object> parameters = ActionContext.getContext()
					.getParameters();
			String ids = ((String[]) parameters.get("ids"))[0];
			blacklistService.delete(BlacklistConfig.class, ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除黑名单失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public long getResultSize() {
		return blacklistService.getBlacklistsCount();
	}

	@Override
	public List<BlacklistConfig> listResults(int from, int length) {
		blacklists = blacklistService.getBlacklist(from, length);
		return blacklists;
	}

	public BlacklistConfig getBlacklistConfig() {
		return blacklist;
	}

	public void setBlacklistConfig(BlacklistConfig blacklist) {
		this.blacklist = blacklist;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return blacklistService.getBlacklistCount(groupOp, criteria);
	}

	@Override
	public List<BlacklistConfig> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return blacklistService.getBlacklists(groupOp, criteria, from, length);
	}

	public List<BlacklistConfig> getBlacklistConfigs() {
		return blacklists;
	}

	public void setBlacklistConfigs(List<BlacklistConfig> blacklists) {
		this.blacklists = blacklists;
	}

	public BlacklistConfig getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(BlacklistConfig blacklist) {
		this.blacklist = blacklist;
	}

	public List<BlacklistConfig> getBlacklists() {
		return blacklists;
	}

	public void setBlacklists(List<BlacklistConfig> blacklists) {
		this.blacklists = blacklists;
	}

}
