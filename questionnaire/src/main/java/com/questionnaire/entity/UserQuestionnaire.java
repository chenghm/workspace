package com.questionnaire.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the user_questionnaire database table.
 * 
 */
@Entity
@Table(name = "T_USER_QUESTIONNAIRE")
@NamedQuery(name = "UserQuestionnaire.findAll", query = "SELECT u FROM UserQuestionnaire u")
@SequenceGenerator(name = "SEQ_USER_QUESTIONNAIRE", sequenceName = "SEQ_USER_QUESTIONNAIRE")
public class UserQuestionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_QUESTIONNAIRE")
	private Integer id;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to Questionnaire
	@ManyToOne
	private Questionnaire questionnaire;

	public UserQuestionnaire() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

}