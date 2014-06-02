package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.LogOperation;

@Controller
@Scope("prototype")
public class LogAction extends QueryAction<LogOperation>{

	private static final long serialVersionUID = 1L;
	
	public LogAction(){
		logSubject = "日志";
	}
	
	

}
