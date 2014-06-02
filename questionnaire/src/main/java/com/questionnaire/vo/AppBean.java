package com.questionnaire.vo;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="AppBean")
public class AppBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Status status;
	private String message;
	private Object data;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
