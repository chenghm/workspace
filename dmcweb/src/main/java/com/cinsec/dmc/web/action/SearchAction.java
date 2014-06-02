package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.SearchRecord;
@Controller
@Scope("prototype")
public class SearchAction extends QueryAction<SearchRecord> {

	private static final long serialVersionUID = 1L;
	public SearchAction(){
		logSubject = "搜索引擎记录";
	}

}
