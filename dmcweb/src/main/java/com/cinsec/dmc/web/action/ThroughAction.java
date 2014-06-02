package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.ThroughRecord;
@Controller
@Scope("prototype")
public class ThroughAction extends QueryAction<ThroughRecord> {

	private static final long serialVersionUID = 1L;
	public ThroughAction(){
		logSubject = "破网行为记录";
	}

}
