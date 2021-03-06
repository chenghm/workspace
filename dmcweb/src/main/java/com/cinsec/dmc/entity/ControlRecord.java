package com.cinsec.dmc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the t_control_record database table.
 * 
 */
@Entity
@Table(name="t_control_record")
public class ControlRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String account;

	private String command;

	@Column(name="control_classify")
	private String controlClassify;

	@Column(name="dest_belong")
	private String destBelong;

	@Column(name="dest_ip")
	private String destIp;

	@Column(name="dest_mac")
	private String destMac;

	@Column(name="dest_port")
	private Integer destPort;

	@Column(name="is_black")
	private String isBlack;

	@Column(name="is_read")
	private String isRead;

	private String password;

	@Column(name="source_belong")
	private String sourceBelong;

	@Column(name="source_ip")
	private String sourceIp;

	@Column(name="source_mac")
	private String sourceMac;

	@Column(name="source_port")
	private Integer sourcePort;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@Column(name="tool_name")
	private String toolName;
	@ManyToOne
	@JoinColumn(name="node_id")
	private Node node;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	public ControlRecord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getControlClassify() {
		return this.controlClassify;
	}

	public void setControlClassify(String controlClassify) {
		this.controlClassify = controlClassify;
	}

	public String getDestBelong() {
		return this.destBelong;
	}

	public void setDestBelong(String destBelong) {
		this.destBelong = destBelong;
	}

	public String getDestIp() {
		return this.destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public String getDestMac() {
		return this.destMac;
	}

	public void setDestMac(String destMac) {
		this.destMac = destMac;
	}

	public Integer getDestPort() {
		return this.destPort;
	}

	public void setDestPort(Integer destPort) {
		this.destPort = destPort;
	}

	public String getIsBlack() {
		return this.isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSourceBelong() {
		return this.sourceBelong;
	}

	public void setSourceBelong(String sourceBelong) {
		this.sourceBelong = sourceBelong;
	}

	public String getSourceIp() {
		return this.sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getSourceMac() {
		return this.sourceMac;
	}

	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}

	public Integer getSourcePort() {
		return this.sourcePort;
	}

	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getToolName() {
		return this.toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

}