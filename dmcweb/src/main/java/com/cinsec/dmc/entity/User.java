package com.cinsec.dmc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;

/**
 * The persistent class for the t_user database table.
 * 
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "chinese_name",length=45)
    private String chineseName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "created_user",length=45)
    private String createdUser;

    @Column(length=200)
    private String descn;

    @Column(length=45)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    @Column(length=256,nullable=false)
    private String password;

    @Column(length=45)
    private String phone;

    @Column
    private Integer status = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "updated_user",length=45)
    private String updatedUser;

    @Column(length=45,unique=true,nullable=false)
    private String username;

    // bi-directional many-to-one association to NodeUser
//    @OneToMany(mappedBy = "user")
//    private List<NodeUser> nodeUsers;

    // bi-directional many-to-one association to RoleUser
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    private List<RoleUser> roleUsers;

//    private Map<String,String> map ;
   
//    private String role;
    
    public User() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return this.createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getDescn() {
        return this.descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatedTime() {
        return this.updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUser() {
        return this.updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    @JSON(serialize = false)
//    public List<NodeUser> getNodeUsers() {
//        return this.nodeUsers;
//    }
//
//    public void setNodeUsers(List<NodeUser> nodeUsers) {
//        this.nodeUsers = nodeUsers;
//    }

//    public NodeUser addNodeUsers(NodeUser nodeUsers) {
//        getNodeUsers().add(nodeUsers);
//        nodeUsers.setUser(this);
//
//        return nodeUsers;
//    }
//
//    public NodeUser removeNodeUsers(NodeUser nodeUsers) {
//        getNodeUsers().remove(nodeUsers);
//        nodeUsers.setUser(null);
//
//        return nodeUsers;
//    }

    @JSON(serialize = false)
    public List<RoleUser> getRoleUsers() {
        return this.roleUsers;
    }

    public void setRoleUsers(List<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public RoleUser addRoleUsers(RoleUser roleUsers) {
        getRoleUsers().add(roleUsers);
        roleUsers.setUser(this);

        return roleUsers;
    }

    public RoleUser removeRoleUsers(RoleUser roleUsers) {
        getRoleUsers().remove(roleUsers);
        roleUsers.setUser(null);

        return roleUsers;
    }
//    @Transient
//	public String getRole() {
//    	List<RoleUser> roleUsers =this.getRoleUsers();
//    	for(RoleUser ru :roleUsers){
//    		role =ru.getRole().getName();
//    	}
//		return role;
//	}

//	public void setRole(String role) {
//		this.role = role;
//	}

//	 @Transient
//	public Map<String, String> getMap() {
//	Map<String,String>	map= new HashMap<String,String>();
//		map.put("id1", "value1");
//		map.put("id2", "value2");
//		return map;
//	}

//	public void setMap(Map<String, String> map) {
//		this.map = map;
//	}
}