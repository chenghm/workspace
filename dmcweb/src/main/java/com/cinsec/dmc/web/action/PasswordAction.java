package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.PasswordRecord;
@Controller
@Scope("prototype")
public class PasswordAction extends QueryAction<PasswordRecord> {

	private static final long serialVersionUID = 1L;
	public PasswordAction(){
		logSubject = "口令信息";
	}

}
