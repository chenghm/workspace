package com.cinsec.dmc.security;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.dao.IUserDao;
import com.cinsec.dmc.entity.User;
import com.cinsec.dmc.exception.SysException;

@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService, Serializable {

	private final class UserDetailsImpl extends User implements UserDetails {
		private static final long serialVersionUID = 2586035525618223858L;

		UserDetailsImpl(User user) {
			setId(user.getId());
			setUsername(user.getUsername());
			setPassword(user.getPassword());
			setStatus(user.getStatus());
			setChineseName(user.getChineseName());
			setEmail(user.getEmail());
			setPhone(user.getPhone());
			setDescn(user.getDescn());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<? extends GrantedAuthority> authorities = null;
			try {
				authorities = userDao.getAuthorities(this);
			} catch (SQLException e) {
				MyUserDetailService.logger.error(e.getMessage());
				throw new SysException(e);
			}
			return authorities;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return this.getStatus() == 1;
		}

	}

	private static final Logger logger = Logger
			.getLogger(MyUserDetailService.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -6294973248618460180L;

	@Autowired
	private IUserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			User user = userDao.findUserByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException(
						"Invalid username/password.");
			}

			userDetails = new UserDetailsImpl(user);

		} catch (SQLException e) {
			MyUserDetailService.logger.error(e.getMessage());
			throw new SysException(e);
		}
		return userDetails;
	}

}
