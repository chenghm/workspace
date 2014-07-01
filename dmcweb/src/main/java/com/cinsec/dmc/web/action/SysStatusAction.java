package com.cinsec.dmc.web.action;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;

import com.cinsec.dmc.util.LinuxSystemTool;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SysStatusAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String duration;
	private String time;
	private String cpu;
	private String memory;
	private String harddisk;

	@Override
	public String execute() throws IOException, InterruptedException {
		duration = LinuxSystemTool.getRuntime();
		double cpuinfo = LinuxSystemTool.getCpuInfo();
		NumberFormat format = NumberFormat.getPercentInstance();
		format.setMinimumFractionDigits(2);
		cpu = format.format(cpuinfo);

		memory = format
				.format((LinuxSystemTool.getMemInfo()[0] - LinuxSystemTool
						.getMemInfo()[1]) / LinuxSystemTool.getMemInfo()[0]);
		
		harddisk = format.format(LinuxSystemTool.getDisk());
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return SUCCESS;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

}
