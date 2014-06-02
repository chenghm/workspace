package com.cinsec.dmc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.dao.IUserDao;
import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.Role;
import com.cinsec.dmc.entity.RoleUser;
import com.cinsec.dmc.entity.User;
import com.cinsec.dmc.exception.SysException;
import com.cinsec.dmc.service.IUserService;
import com.cinsec.dmc.util.Constants;
import com.cinsec.dmc.util.UserContext;

@Service
public class UserService extends BaseService<User> implements IUserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUser(int id) {
		return this.getEntity(User.class, id);

	}

	//
	// @Override
	// public User findUserByUsername(String username) {
	// return userDao.findUserByUsername(username);
	// }
	//
	@Override
	public boolean isDuplicated(User user, String propertyName,
			Object propertyValue) {

		List<User> records = userDao.findEntityByProperty(User.class,
				propertyName, propertyValue);
		if (CollectionUtils.isEmpty(records)) {
			return false;
		}
		if (user.getId() == null) {
			return true;
		}
		for (User record : records) {
			if (record.getId() != user.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public int createUser(User user) throws Exception {
		String encodedPassword = passwordEncoder
				.encode(Constants.DEFAULT_PASSWORD);
		user.setPassword(encodedPassword);
		String currentUser = UserContext.getCurrentUserName();
		Date currentTime = new Date();
		user.setCreatedTime(currentTime);
		user.setCreatedUser(currentUser);
		// user.setUpdatedTime(currentTime);
		// user.setUpdatedUser(currentUser);
		return userDao.createUser(user);
	}

	//
	@Override
	public List<User> getUsers(int from, int length) {
		return userDao.getUsers(from, length);
	}

	//
	// @Override
	// @Transactional
	// public void deleteUsers(String userIds) {
	// String[] ids = userIds.split(",");
	// for (String id : ids) {
	// userDao.deleteUser(Integer.valueOf(id));
	// }
	// }
	//
	@Override
	@Transactional
	public void modifyUser(User user) {
		User u = getUser(user.getId());
		u.setUpdatedTime(new Date());
		u.setUpdatedUser(UserContext.getCurrentUserName());
		u.setChineseName(user.getChineseName());
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setPhone(user.getPhone());
		u.setDescn(user.getDescn());
		// this.modify(user);
		// userDao.modifyUser(user);
	}

	//
	// @Override
	// public User getCurrentUser() {
	// SecurityContext context = SecurityContextHolder.getContext();
	// Authentication authentication = context.getAuthentication();
	// if (authentication == null) {
	// return null;
	// }
	//
	// return (User) authentication.getPrincipal();
	// }
	//
	// @Override
	// public boolean validatePassword(String password) {
	// return passwordEncoder
	// .matches(password, getCurrentUser().getPassword());
	// }
	//
	// @Override
	// public void setCurrentUser(User user) {
	// Collection<? extends GrantedAuthority> authorities = userDao
	// .getAuthorities(user);
	// Authentication authentication = new UsernamePasswordAuthenticationToken(
	// user, user.getPassword(), authorities);
	// SecurityContextHolder.getContext().setAuthentication(authentication);
	// }
	//
	@Override
	public long getUsersCount() {
		return userDao.getUsersCount();
	}

	//
	// @Override
	// public Map<Integer, String> getRoles() {
	// return userDao.getRoles();
	// }
	//
	// @Override
	// public Boolean existUser(User user) {
	// User u = userDao.findUserByUsername(user.getUsername());
	// if (user.getId() == null) {
	// return u != null ? true : false;
	// }
	//
	// return u != null && u.getId() != user.getId() ? true : false;
	// }
	//
	// @Override
	// @Transactional
	// public void modifyPassword(String newPassword) {
	// String encodedPassword = passwordEncoder.encode(newPassword);
	// User user = new User();
	// user.setId(getCurrentUser().getId());
	// user.setPassword(encodedPassword);
	// user.setUpdatedTime(new Date());
	// user.setUpdatedUser(user.getUsername());
	// userDao.modifyPassword(user);
	// }
	//
	// @Override
	// public Long getPages() {
	// Long total = userDao.getUsersCount();
	// return (total - 1) / Constants.PAGE_SIZE + 1;
	// }
	//
	// @Override
	// public List<User> findUsersByName(String name) {
	// return userDao.findUsersByUsernameAndChineseName(name);
	// }
	//
	// @Override
	// public List<String> getUsernameList() {
	// return userDao.getUsernameList();
	// }
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
	@Transactional
	public int createUser(User user, int roleId) throws Exception {
		String encodedPassword = passwordEncoder
				.encode(Constants.DEFAULT_PASSWORD);
		user.setPassword(encodedPassword);
		String currentUser = UserContext.getCurrentUserName();
		Date currentTime = new Date();
		user.setCreatedTime(currentTime);
		user.setCreatedUser(currentUser);

		List<RoleUser> roleUsers = new ArrayList<RoleUser>(1);
		RoleUser ru = new RoleUser();
		Role role = new Role();
		role.setId(roleId);
		ru.setRole(role);
		ru.setUser(user);
		roleUsers.add(ru);
		user.setRoleUsers(roleUsers);
		return userDao.createUser(user);
	}

	@Override
	public boolean validatePassword(String password) {
		return passwordEncoder.matches(password, UserContext.getCurrentUser()
				.getPassword());
	}

	@Override
	@Transactional
	public void modifyPassword(String newPassword) {
		User user = this.getEntity(User.class,UserContext.getCurrentUser().getId());
		String encodedPassword = passwordEncoder.encode(newPassword);
		// User user = new User();
		// user.setId(UserContext.getCurrentUser().getId());
		user.setPassword(encodedPassword);
		user.setUpdatedTime(new Date());
		user.setUpdatedUser(user.getUsername());
		// userDao.modifyPassword(user);
	}

	@Override
	public List<User> getUserByRole(int roleId) {
		
		return userDao.getUserByRole(roleId);
	}

	@Override
	public List<User> getUserNoRole(int roleId) {
		return userDao.getUserNoRole(roleId);
	}
}
