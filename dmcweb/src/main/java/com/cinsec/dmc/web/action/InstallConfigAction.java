package com.cinsec.dmc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller
public class InstallConfigAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String device;
	private String ipaddr;
	private String netmask;
	private String gateway;
	private String dns1;
	private String dns2;
	private String message;
	
	@Override
	public String execute() {
		try {
			String path = "/etc/sysconfig/network-scripts/ifcfg-"+device;
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			if(StringUtils.isNotEmpty(ipaddr)){
				properties.setProperty("IPADDR", ipaddr);
			}
			
			if(StringUtils.isNotEmpty(netmask)){
				properties.setProperty("NETMASK", netmask);
			}
			
			if(StringUtils.isNotEmpty(gateway)){
				properties.setProperty("GATEWAY", gateway);
			}
			
			if(StringUtils.isNotEmpty(dns1)){
				properties.setProperty("DNS1", dns1);
			}
			
			if(StringUtils.isNotEmpty(dns2)){
				properties.setProperty("DNS2", dns2);
			}
			FileOutputStream fos = new FileOutputStream(file); 
			properties.store(fos,null);
			fos.close();
			fis.close();
			message = "设置成功！";
		} catch (Exception e) {
			message="设置失败："+e.getMessage();
		}
		return SUCCESS;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
