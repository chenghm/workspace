/**
 * Created on 2013年12月19日
 * filename: IResourceDao.java
 * Description: 
 * Copyright: Copyright(c)2013
 * Company: Mars
 */
package com.cinsec.dmc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cinsec.dmc.entity.Resource;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.User;

/**
 * Title: Class XXXX Description:
 * 
 * 
 * @author chenghongming
 * @version xxx
 */
public interface IResourceDao extends IBaseDao<Resource> {

    /**
     * 
     * TODO：replace with the method comments here!!!
     * 
     * @author chenghongming
     * @version
     * @return
     * @throws java.sql.SQLException
     */
    public List<Resource> findAll() throws SQLException;

	public List<Resource> getRights(Role role);

	List<Resource> getUserRights(User user);

}
