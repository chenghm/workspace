package com.cinsec.dmc.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.ParameterConfig;
import com.cinsec.dmc.service.IParameterService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ParameterAction extends BaseAction<ParameterConfig> {

	private ParameterConfig parameter;
	private List<ParameterConfig> parameters;

	private static final long serialVersionUID = 1L;
	@Autowired
	private IParameterService parameterService;
	private String ajaxResult;

	public String find() {
		parameterService.writeOperLog("查看参数配置信息");
		return this.refreshGridModel();
	}

	public String view() {
		parameterService.writeOperLog("查看参数配置信息");
		parameter = parameterService.getEntity(ParameterConfig.class,
				parameter.getId());

		if (parameter == null) {
			ajaxResult = "记录不存在";
			return ERROR;
		}

		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	public String create() {
		parameterService.writeOperLog("创建参数配置信息");
		try {
			if (!validateData()) {
				ajaxResult = "创建新记录验证失败！";
				return ERROR;
			}
			parameter = parameterService.createParameter(parameter);
			ajaxResult = SUCCESS;
			// parameter = null;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "创建记录失败!" + e.getMessage();
			return ERROR;
		}
	}

	private boolean validateData() {
		boolean result = true;
		String parameterCode = parameter.getParameterCode();
		String parameterName = parameter.getParameterName();
		String parameterValue = parameter.getParameterValue();

		if (StringUtils.isEmpty(parameterCode)) {
			messages.put("error_parameter_parameterCode", "参数编码不能为空！");
			result = false;
		}

		if (StringUtils.isEmpty(parameterName)) {
			messages.put("error_parameter_parameterName", "参数名不能为空！");
			result = false;
		}

		if (StringUtils.isEmpty(parameterValue)) {
			messages.put("error_parameter_parameterValue", "参数值不能为空！");
			result = false;
		}

		return result;
	}

	public String update() {
		parameterService.writeOperLog("更新参数配置信息");

		if (!validateData()) {
			ajaxResult = "更新记录验证失败！";
			return ERROR;
		}
		try {
			parameter = parameterService.modifyParameter(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "更新记录失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	// 删除parameter
	public String delete() {
		parameterService.writeOperLog("删除参数配置信息");
		try {
			Map<String, Object> parameters = ActionContext.getContext()
					.getParameters();
			String ids = ((String[]) parameters.get("ids"))[0];
			parameterService.delete(ParameterConfig.class, ids);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			ajaxResult = "删除记录失败!" + e.getMessage();
			return ERROR;
		}
		ajaxResult = SUCCESS;
		return SUCCESS;
	}

	@Override
	public long getResultSize() {
		return parameterService.getEntityCount(ParameterConfig.class);
	}

	@Override
	public List<ParameterConfig> listResults(int from, int length) {
		parameters = parameterService.getEntityList(ParameterConfig.class,
				from, length);
		return parameters;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return parameterService.getResultCount(ParameterConfig.class, groupOp,
				criteria);
	}

	@Override
	public List<ParameterConfig> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return parameterService.getResultList(ParameterConfig.class, groupOp,
				criteria, from, length);
	}

	public List<ParameterConfig> getParameterConfigs() {
		return parameters;
	}

	public void setParameterConfigs(List<ParameterConfig> parameters) {
		this.parameters = parameters;
	}

	public ParameterConfig getParameter() {
		return parameter;
	}

	public void setParameter(ParameterConfig parameter) {
		this.parameter = parameter;
	}

	public List<ParameterConfig> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterConfig> parameters) {
		this.parameters = parameters;
	}

	public void setParameterService(IParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
