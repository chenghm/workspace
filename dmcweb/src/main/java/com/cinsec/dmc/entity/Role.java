package com.cinsec.dmc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
 * The persistent class for the t_role database table.
 * 
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "created_user",length=45)
    private String createdUser;

    @Column(length=200)
    private String descn;

    @Column(length=45,unique=true,nullable=false)
    private String code;
    
    @Column(length=45,nullable=false,unique=true)
    private String name;

    @Column
    private Integer status = 1;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "updated_user",length=45)
    private String updatedUser;

    // bi-directional many-to-one association to RoleResource
    @OneToMany(mappedBy = "role")
    private Set<RoleResource> roleResources;

    // bi-directional many-to-one association to RoleUser
    @OneToMany(mappedBy = "role")
    private Set<RoleUser> roleUsers;

    public Role() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @JSON(serialize = false)
    public Set<RoleResource> getRoleResources() {
        return this.roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }

    public RoleResource addRoleResources(RoleResource roleResources) {
        getRoleResources().add(roleResources);
        roleResources.setRole(this);

        return roleResources;
    }

    public RoleResource removeRoleResources(RoleResource roleResources) {
        getRoleResources().remove(roleResources);
        roleResources.setRole(null);

        return roleResources;
    }

    @JSON(serialize = false)
    public Set<RoleUser> getRoleUsers() {
        return this.roleUsers;
    }

    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public RoleUser addRoleUsers(RoleUser roleUsers) {
        getRoleUsers().add(roleUsers);
        roleUsers.setRole(this);

        return roleUsers;
    }

    public RoleUser removeRoleUsers(RoleUser roleUsers) {
        getRoleUsers().remove(roleUsers);
        roleUsers.setRole(null);

        return roleUsers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}