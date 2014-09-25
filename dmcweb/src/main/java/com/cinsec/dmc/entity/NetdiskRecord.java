package com.cinsec.dmc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the t_netdisk_record database table.
 * 
 */
@Entity
@Table(name="t_netdisk_record")
public class NetdiskRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="node_id")
	private Node node;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String account;

	@Column(name="dest_belong")
	private String destBelong;

	@Column(name="dest_ip")
	private String destIp;

	@Column(name="dest_mac")
	private String destMac;

	@Column(name="file_classify")
	private String fileClassify;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_old_name")
	private String fileOldName;

	@Column(name="file_size")
	private Integer fileSize;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String url;

	public NetdiskRecord() {
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

	public String getFileClassify() {
		return this.fileClassify;
	}

	public void setFileClassify(String fileClassify) {
		this.fileClassify = fileClassify;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileOldName() {
		return this.fileOldName;
	}

	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
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

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}