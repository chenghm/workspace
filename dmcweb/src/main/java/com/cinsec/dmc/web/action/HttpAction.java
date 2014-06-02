package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.HttpRecord;
@Controller
@Scope("prototype")
public class HttpAction extends QueryAction<HttpRecord> {

	private static final long serialVersionUID = 1L;
	public HttpAction(){
		logSubject = "网页浏览";
	}

}
