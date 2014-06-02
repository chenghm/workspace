package com.questionnaire.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.questionnaire.dao.IManagerDao;
import com.questionnaire.entity.Manager;
import com.questionnaire.exception.SysException;
import com.questionnaire.service.IManagerService;

@Service
public class ManagerService extends BaseService<Manager> implements
		IManagerService {
	private static final long serialVersionUID = -8043061497680405979L;

	@Autowired
	private IManagerDao managerDao;

	@Override
	public boolean validatePassword(String password) {
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			User user = (User) context.getAuthentication().getPrincipal();
			Manager manager = managerDao.findUserByUsername(user.getUsername());
			return  manager.getPassword().equals(password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	@Transactional
	public void modifyPassword(String newPassword) {
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			User user = (User) context.getAuthentication().getPrincipal();
			Manager manager = managerDao.findUserByUsername(user.getUsername());
			manager.setPassword(newPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

}
