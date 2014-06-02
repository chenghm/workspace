package com.questionnaire.service;

import java.util.List;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.User;

public interface IUserService extends IBaseService<User> {

	long getUsersCount();

	List<User> getUsers(int from, int length);

	long getUsersCount(String groupOp, List<Criterion> criteria);

	List<User> getUsers(String groupOp, List<Criterion> criteria, int from,
			int length);
	
	User findUserByUsername(String username);
	
	User findUserByUsernameAndPassword(String username,String password);
	
	User createUser(User user);
	
	

}
