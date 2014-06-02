package com.cinsec.dmc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the t_mail_record database table.
 * 
 */
@Entity
@Table(name="t_mail_record")
public class MailRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String account;

	@Column(name="attach_name")
	private String attachName;

	@Column(name="attach_old_name")
	private String attachOldName;

	@Column(name="attach_size")
	private Integer attachSize;

	@Lob
	private String bcc;

	@Lob
	private String cc;

	private String classify;

	@Column(name="content_name")
	private String contentName;

	@Lob
	private String cookie;

	@Column(name="dest_belong")
	private String destBelong;

	@Column(name="dest_ip")
	private String destIp;

	@Column(name="dest_mac")
	private String destMac;

	private String from;

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

	@Lob
	private String subject;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@Lob
	private String to;

	private String url;

	public MailRecord() {
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

	public String getAttachName() {
		return this.attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getAttachOldName() {
		return this.attachOldName;
	}

	public void setAttachOldName(String attachOldName) {
		this.attachOldName = attachOldName;
	}

	public Integer getAttachSize() {
		return this.attachSize;
	}

	public void setAttachSize(Integer attachSize) {
		this.attachSize = attachSize;
	}

	public String getBcc() {
		return this.bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getClassify() {
		return this.classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getContentName() {
		return this.contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getCookie() {
		return this.cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
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

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}