package com.cinsec.dmc.util;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cinsec.dmc.entity.User;

@Component
public class ContextFactory implements ApplicationContextAware {

	// Spring上下文
	private static ApplicationContext appContext;

	/**
	 * Declare：取Spring上下文Bean
	 * 
	 * @param beanId
	 *            bean名称
	 * @return Object 实体
	 */
	public static Object getBean(String beanId) {

		if (beanId == null) {
			return null;
		}

		if (null == ContextFactory.appContext) {
			return null;
		}

		return ContextFactory.appContext.getBean(beanId);
	}

	public static String getUserIp() {
		try {
			return ServletActionContext.getRequest().getRemoteAddr();
		} catch (Exception e) {
			return null;
		}
	}

	public static User getUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		}
		return null;
	}

	public static String getUsername() {
		User user = getUser();
		return user == null ? "anonymousUser" : user.getUsername();
	}

	public static void initMDC() {
		MDC.put("username", getUsername());
		MDC.put("userip", getUserIp());
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		ContextFactory.appContext = appContext;
	}

}
