package com.cinsec.dmc.dao;

import com.cinsec.dmc.entity.ParameterConfig;

public interface IParameterDao  extends IBaseDao<ParameterConfig>{

	String getParameter(String key);

}
