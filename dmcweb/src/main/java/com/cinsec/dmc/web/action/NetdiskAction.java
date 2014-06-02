package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.NetdiskRecord;

@Controller
@Scope("prototype")
public class NetdiskAction extends QueryAction<NetdiskRecord> {

	private static final long serialVersionUID = 1L;

	public NetdiskAction() {
		logSubject = "网盘记录";
	}

}
