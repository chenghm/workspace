package com.cinsec.dmc.service;

import com.cinsec.dmc.entity.ParameterConfig;

public interface IParameterService extends IBaseService<ParameterConfig> {

	ParameterConfig createParameter(ParameterConfig parameter) throws Exception;

	ParameterConfig modifyParameter(ParameterConfig parameter);
	
	String getParameter(String key);

}
