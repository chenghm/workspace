package com.cinsec.dmc.web.action;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("session")
public class IntallConfigAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<String> networkNames = new ArrayList<String>();
	static{
		
	}
	
	public String showNetwork() throws SocketException {

		Enumeration<NetworkInterface> en = NetworkInterface
				.getNetworkInterfaces();
		while (en.hasMoreElements()) {
			networkNames.add(en.nextElement().getName());
		}
		return SUCCESS;

	}

	public List<String> getNetworkNames() {
		return networkNames;
	}

	public void setNetworkNames(List<String> networkNames) {
		this.networkNames = networkNames;
	}

}
