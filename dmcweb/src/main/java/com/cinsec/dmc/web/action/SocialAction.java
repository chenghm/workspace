package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.SocialRecord;
@Controller
@Scope("prototype")
public class SocialAction extends QueryAction<SocialRecord> {

	private static final long serialVersionUID = 1L;
	public SocialAction(){
		logSubject = "社交记录";
	}

}
