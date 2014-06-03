package com.questionnaire.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@Table(name="T_USER")
//@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String age;

	private String education;

	private String gender;

	private String income;

	private String occupation;

	private String password;

	private Integer score;

	private String username;

	private String email;

	private String imei;

	// bi-directional many-to-one association to UserQuestionnaire
	@OneToMany(mappedBy = "user")
	private List<UserQuestionnaire> userQuestionnaires;

	@OneToMany(mappedBy = "user",cascade=CascadeType.MERGE)
	private List<UserQuestion> userQuestions;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIncome() {
		return this.income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON(serialize = false)
	@JsonIgnore
	public List<UserQuestionnaire> getUserQuestionnaires() {
		return this.userQuestionnaires;
	}

	public void setUserQuestionnaires(List<UserQuestionnaire> userQuestionnaires) {
		this.userQuestionnaires = userQuestionnaires;
	}

	public UserQuestionnaire addUserQuestionnaire(
			UserQuestionnaire userQuestionnaire) {
		getUserQuestionnaires().add(userQuestionnaire);
		userQuestionnaire.setUser(this);

		return userQuestionnaire;
	}

	public UserQuestionnaire removeUserQuestionnaire(
			UserQuestionnaire userQuestionnaire) {
		getUserQuestionnaires().remove(userQuestionnaire);
		userQuestionnaire.setUser(null);

		return userQuestionnaire;
	}

	@JSON(serialize = false)
	@JsonIgnore
	public List<UserQuestion> getUserQuestions() {
		return userQuestions;
	}

	public void setUserQuestions(List<UserQuestion> userQuestions) {
		this.userQuestions = userQuestions;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}