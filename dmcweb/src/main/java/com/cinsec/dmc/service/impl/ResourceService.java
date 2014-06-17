package com.cinsec.dmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.dao.IResourceDao;
import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.User;
import com.cinsec.dmc.service.IResourceService;
import com.cinsec.dmc.util.ContextFactory;

@Service
public class ResourceService extends BaseService<Resource> implements IResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5950219025202734648L;
	@Autowired
	private IResourceDao resourceDao;
	@Override
	public Resource getEntityByCode(String code){
		List<Resource> records =   (List<Resource>) this.findEntityByProperty(Resource.class,
				"code", code);
		if(CollectionUtils.isEmpty(records)){
			return null;
		}
		
		return records.get(0);
	}
	
	@Override
	public List<Resource> getCurrentUserResources(){
		User user  =ContextFactory.getUser();
		if(user==null){
			return new ArrayList<Resource>(0);
		}
		return resourceDao.getUserRights(user);
	}


}
