package com.cinsec.dmc.web.action;

import org.springframework.stereotype.Controller;

import com.cinsec.dmc.util.ExecuteLinuxCmd;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SysControlAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String restart(){
		
		ExecuteLinuxCmd.exec("reboot");
		return SUCCESS;
	}
	
	
	public String stop(){
		ExecuteLinuxCmd.exec("shutdown -h now");
		return SUCCESS;
	}
	

}
