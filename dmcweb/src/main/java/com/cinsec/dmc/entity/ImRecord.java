package com.cinsec.dmc.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the t_im_record database table.
 * 
 */
@Entity
@Table(name="t_im_record")
public class ImRecord implements Serializable {
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

	@Column(name="dest_account")
	private String destAccount;

	@Column(name="dest_belong")
	private String destBelong;

	@Column(name="dest_ip")
	private String destIp;

	@Column(name="dest_mac")
	private String destMac;

	@Column(name="dest_password")
	private String destPassword;

	@Column(name="file_classify")
	private String fileClassify;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_old_name")
	private String fileOldName;

	@Column(name="file_size")
	private Integer fileSize;

	@Column(name="im_classify")
	private String imClassify;

	@Column(name="is_black")
	private String isBlack;

	@Column(name="is_read")
	private String isRead;

	@Lob
	private String message;

	@Column(name="source_account")
	private String sourceAccount;

	@Column(name="source_belong")
	private String sourceBelong;

	@Column(name="source_ip")
	private String sourceIp;

	@Column(name="source_mac")
	private String sourceMac;

	@Column(name="source_password")
	private String sourcePassword;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public ImRecord() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestAccount() {
		return this.destAccount;
	}

	public void setDestAccount(String destAccount) {
		this.destAccount = destAccount;
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

	public String getDestPassword() {
		return this.destPassword;
	}

	public void setDestPassword(String destPassword) {
		this.destPassword = destPassword;
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

	public String getImClassify() {
		return this.imClassify;
	}

	public void setImClassify(String imClassify) {
		this.imClassify = imClassify;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSourceAccount() {
		return this.sourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
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

	public String getSourcePassword() {
		return this.sourcePassword;
	}

	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}