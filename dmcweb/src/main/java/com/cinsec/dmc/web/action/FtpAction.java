package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.FtpRecord;
@Controller
@Scope("prototype")
public class FtpAction extends QueryAction<FtpRecord> {

	private static final long serialVersionUID = 1L;
	public FtpAction(){
		logSubject = "FTP记录";
	}

}
