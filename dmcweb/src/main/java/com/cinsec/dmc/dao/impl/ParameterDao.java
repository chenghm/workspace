/**
 * Created on 2013年12月19日
 * filename: UserDao.java
 * Description: 
 * Copyright: Copyright(c)2013
 * Company: Mars
 */
package com.cinsec.dmc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.cinsec.dmc.dao.IParameterDao;
import com.cinsec.dmc.entity.ParameterConfig;

/**
 * Title: Class XXXX Description:
 * 
 * 
 * @author chenghongming
 * @version xxx
 */
@Repository
@SuppressWarnings({ "unchecked" })
public class ParameterDao extends BaseDao<ParameterConfig> implements IParameterDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getParameter(String key){
		
		String jql = " from ParameterConfig p where p.parameterCode=:code and p.status=:status";
		Map<String, Object> parVals = new HashMap<String, Object>(2);
		parVals.put("code", key);
		parVals.put("status", "1");
		List<ParameterConfig> list = (List<ParameterConfig>) this.getEntityList(jql, parVals);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0).getParameterValue();
		
	}

}
