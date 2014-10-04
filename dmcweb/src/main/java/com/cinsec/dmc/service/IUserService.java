package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.User;

public interface IUserService extends IBaseService<User> {

	long getUsersCount();

	List<User> getUsers(int from,int length);

	int createUser(User user) throws Exception;
	
	int createUser(User user,int roleId) throws Exception;

	void modifyUser(User user);

	User getUser(int id);

	boolean isDuplicated(User user, String propertyName, Object propertyValue);

	long getUsersCount(String groupOp,List<Criterion> criteria);

	List<User> getUsers(String groupOp,List<Criterion> criteria, int from, int length);

	boolean validatePassword(String currentPassword);

	void modifyPassword(String newPassword);

	List<User> getUserByRole(int roleId);

	List<User> getUserNoRole(int roleId);

	Integer createUser(User user, String nodeIds);

	void modifyUser(User user, String nodeIds);

}
