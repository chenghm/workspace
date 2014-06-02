/**
 * Created on 2013年12月19日
 * filename: IUserDao.java
 * Description: 
 * Copyright: Copyright(c)2013
 * Company: Mars
 */
package com.cinsec.dmc.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.User;

/**
 * Title: Class XXXX Description:
 * 
 * 
 * @author chenghongming
 * @version xxx
 */
@SuppressWarnings("rawtypes")
public interface IUserDao extends IBaseDao {
    User findUserByUsername(String username) throws SQLException;

    Collection<? extends GrantedAuthority> getAuthorities(User user) throws SQLException;

	long getUsersCount();

	List<User> getUsers(int pageNo, int pageSize);

	int createUser(User user) throws  Exception;

	long getUsersCount(String groupOp,List<Criterion> criteria) throws Exception;

	List<User> getUsers(String groupOp,List<Criterion> criteria, int from, int length) throws Exception;

	List<User> getUserByRole(int roleId);

	List<User> getUserNoRole(int roleId);


}
