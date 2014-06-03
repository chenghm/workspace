package com.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the manager database table.
 * 
 */
@Entity
@NamedQuery(name="Manager.findAll", query="SELECT m FROM Manager m")
@Table(name="T_MANAGER")
//@SequenceGenerator(name = "SEQ_MANAGER", sequenceName = "SEQ_MANAGER")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_MANAGER")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String password;

	private String username;

	public Manager() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}