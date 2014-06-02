package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.ImRecord;

@Controller
@Scope("prototype")
public class ImAction extends QueryAction<ImRecord> {

	private static final long serialVersionUID = 1L;

	public ImAction() {
		logSubject = "即时通讯文件记录";
	}

}
