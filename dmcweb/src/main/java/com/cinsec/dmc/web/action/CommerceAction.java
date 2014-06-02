package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.CommerceRecord;
@Controller
@Scope("prototype")
public class CommerceAction extends QueryAction<CommerceRecord> {

	private static final long serialVersionUID = 1L;
	public CommerceAction(){
		logSubject = "电子商务记录";
	}

}
