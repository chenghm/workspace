package com.cinsec.dmc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the t_node database table.
 * 
 */
@Entity
@Table(name="t_node")
@NamedQuery(name="Node.findAll", query="SELECT n FROM Node n")
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="file_path", length=255)
	private String filePath;

	@Column(name="file_size")
	private Integer fileSize;

	@Column(name="is_chat", length=1)
	private String isChat;

	@Column(name="is_commmerce", length=1)
	private String isCommmerce;

	@Column(name="is_control", length=1)
	private String isControl;

	@Column(name="is_ftp", length=1)
	private String isFtp;

	@Column(name="is_http", length=1)
	private String isHttp;

	@Column(name="is_mail", length=1)
	private String isMail;

	@Column(name="is_netdisk", length=1)
	private String isNetdisk;

	@Column(name="is_password", length=1)
	private String isPassword;

	@Column(name="is_search", length=1)
	private String isSearch;

	@Column(name="is_social", length=1)
	private String isSocial;

	@Column(name="is_through", length=1)
	private String isThrough;

	@Column(length=45)
	private String monitor;

	@Column(nullable=false)
	private Integer number;

	@Column(length=255)
	private String remark;
	
	
	 @OneToMany(mappedBy = "node",cascade=CascadeType.ALL)
	    private List<UserNode> userNodes;
	
	
//	private String catchType;

	public List<UserNode> getUserNodes() {
		return userNodes;
	}

	public void setUserNodes(List<UserNode> userNodes) {
		this.userNodes = userNodes;
	}

	@Transient
	public String getCatchType() {
		String catchType="";
		if("1".equals(isHttp)){
			catchType+=",网页浏览";
		}
		if("1".equals(isSearch)){
			catchType+=",搜索引擎";
		}
		if("1".equals(isThrough)){
			catchType+=",破网行为";
		}
		if("1".equals(isCommmerce)){
			catchType+=",电子商务";
		}
		if("1".equals(isSocial)){
			catchType+=",社交网络";
		}
		if("1".equals(isPassword)){
			catchType+=",口令信息";
		}
		if("1".equals(isFtp)){
			catchType+=",FTP信息";
		}
		if("1".equals(isNetdisk)){
			catchType+=",网盘信息";
		}
		if("1".equals(isControl)){
			catchType+=",远程控制";
		}
		if("1".equals(isChat)){
			catchType+=",网络聊天";
		}
		if("1".equals(isMail)){
			catchType+=",电子邮件";
		}
		if(!"".equals(catchType)){
			catchType = catchType.substring(1);
		}
		return catchType;
	}

//	public void setCatchType(String catchType) {
//		this.catchType = catchType;
//	}

	public Node() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getIsChat() {
		return this.isChat;
	}

	public void setIsChat(String isChat) {
		this.isChat = isChat;
	}

	public String getIsCommmerce() {
		return this.isCommmerce;
	}

	public void setIsCommmerce(String isCommmerce) {
		this.isCommmerce = isCommmerce;
	}

	public String getIsControl() {
		return this.isControl;
	}

	public void setIsControl(String isControl) {
		this.isControl = isControl;
	}

	public String getIsFtp() {
		return this.isFtp;
	}

	public void setIsFtp(String isFtp) {
		this.isFtp = isFtp;
	}

	public String getIsHttp() {
		return this.isHttp;
	}

	public void setIsHttp(String isHttp) {
		this.isHttp = isHttp;
	}

	public String getIsMail() {
		return this.isMail;
	}

	public void setIsMail(String isMail) {
		this.isMail = isMail;
	}

	public String getIsNetdisk() {
		return this.isNetdisk;
	}

	public void setIsNetdisk(String isNetdisk) {
		this.isNetdisk = isNetdisk;
	}

	public String getIsPassword() {
		return this.isPassword;
	}

	public void setIsPassword(String isPassword) {
		this.isPassword = isPassword;
	}

	public String getIsSearch() {
		return this.isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	public String getIsSocial() {
		return this.isSocial;
	}

	public void setIsSocial(String isSocial) {
		this.isSocial = isSocial;
	}

	public String getIsThrough() {
		return this.isThrough;
	}

	public void setIsThrough(String isThrough) {
		this.isThrough = isThrough;
	}

	public String getMonitor() {
		return this.monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}