package com.questionnaire.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.questionnaire.dao.IUserDao;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.User;
import com.questionnaire.exception.SysException;
import com.questionnaire.service.IUserService;
@Service
public class UserService extends BaseService<User> implements IUserService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserDao userDao;
	@Override
	public long getUsersCount() {
		return userDao.getUsersCount();
	}

	@Override
	public List<User> getUsers(int from, int length) {
		return userDao.getUsers(from, length);
	}

	@Override
	public long getUsersCount(String groupOp, List<Criterion> criteria) {
		try {
			return userDao.getUsersCount(groupOp, criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public List<User> getUsers(String groupOp, List<Criterion> criteria,
			int from, int length) {
		try {
			return userDao.getUsers(groupOp, criteria, from, length);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return userDao.findUserByUsernameAndPassword(username,password);
	}

	@Override
	@Transactional
	public User createUser(User user) {
		try {
			return userDao.create(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

}
