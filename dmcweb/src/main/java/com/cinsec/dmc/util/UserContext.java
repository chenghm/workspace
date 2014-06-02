package com.cinsec.dmc.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cinsec.dmc.entity.User;

public class UserContext {

	public static User getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (User) authentication.getPrincipal();
	}

	/*
	 * public static void setCurrentUser(User user) { Collection<? extends
	 * GrantedAuthority> authorities = userDao.getAuthorities(user);
	 * Authentication authentication = new
	 * UsernamePasswordAuthenticationToken(user, user.getPassword(),
	 * authorities);
	 * SecurityContextHolder.getContext().setAuthentication(authentication); }
	 */

	public static String getCurrentUserName() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return "anonymousUser";
		}

		Object principal = authentication.getPrincipal();
		if (principal instanceof User) {
			return ((User) principal).getUsername();
		}
		return String.valueOf(principal);
	}
}
