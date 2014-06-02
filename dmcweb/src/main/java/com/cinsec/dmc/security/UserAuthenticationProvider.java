package com.cinsec.dmc.security;

import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.dao.IUserDao;
import com.cinsec.dmc.entity.User;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider  {

	
	@Autowired
	private IUserDao userDao;
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		try {
			User user = userDao.findUserByUsername(authentication.getName());
			if (user != null) {
				Collection<? extends GrantedAuthority> authorities 	 = userDao.getAuthorities(user);
				return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
						authorities);
			} else {
				throw new BadCredentialsException("Try again");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
