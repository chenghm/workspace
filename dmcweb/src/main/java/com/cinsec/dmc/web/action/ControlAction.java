package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.ControlRecord;
@Controller
@Scope("prototype")
public class ControlAction extends QueryAction<ControlRecord> {

	private static final long serialVersionUID = 1L;
	public ControlAction(){
		logSubject = "远程控制记录";
	}

}
