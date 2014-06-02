package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.entity.Resource;

public interface IResourceService extends IBaseService<Resource> {

	Resource getEntityByCode(String parentCode);

	List<Resource> getCurrentUserResources();

}
