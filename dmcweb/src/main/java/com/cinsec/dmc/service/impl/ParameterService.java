package com.cinsec.dmc.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IParameterDao;
import com.cinsec.dmc.entity.ParameterConfig;
import com.cinsec.dmc.service.IParameterService;
import com.cinsec.dmc.util.UserContext;

@Service
public class ParameterService extends BaseService<ParameterConfig> implements IParameterService{

	@Autowired
	private IParameterDao parameterDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public ParameterConfig createParameter(ParameterConfig parameter) throws Exception {
		String currentUser = UserContext.getCurrentUserName();
		Date currentTime = new Date();
		parameter.setCreatedTime(currentTime);
		parameter.setCreatedUser(currentUser);

		return parameterDao.create(parameter);
	}

	@Override
	@Transactional
	public ParameterConfig modifyParameter(ParameterConfig parameter) {
		ParameterConfig b = getEntity(ParameterConfig.class, parameter.getId());
		b.setUpdatedTime(new Date());
		b.setUpdatedUser(UserContext.getCurrentUserName());
		b.setParameterCode(parameter.getParameterCode());
		b.setParameterName(parameter.getParameterName());
		b.setParameterValue(parameter.getParameterValue());
		b.setStatus(parameter.getStatus());
		b.setDescn(parameter.getDescn());
		return b;
	}

	@Override
	public String getParameter(String key) {
		return parameterDao.getParameter(key);
	}

}
