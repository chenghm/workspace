package com.cinsec.dmc.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("session")
public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String header() {
		return "header";
	}

	public String menu() {
		return "menu";
	}

	public String main() {
		return "main";
	}

	public String footer() {
		return "footer";
	}

}
