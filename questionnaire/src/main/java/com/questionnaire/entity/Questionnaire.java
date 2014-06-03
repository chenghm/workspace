package com.questionnaire.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.questionnaire.util.CustomDateSerializer;

/**
 * The persistent class for the questionnaire database table.
 * 
 */
@Entity
@NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q")
@Table(name = "T_QUESTIONNAIRE")
//@SequenceGenerator(name = "SEQ_QUESTIONNAIRE", sequenceName = "SEQ_QUESTIONNAIRE")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUESTIONNAIRE")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String age;

	private String education;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endtime;

	private String gender;

	private String income;

	private String name;

	private String occupation;

	private Integer point;

	// bi-directional many-to-one association to Question
	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.REMOVE)
	private List<Question> questions;

	// bi-directional many-to-one association to UserQuestionnaire
	@OneToMany(mappedBy = "questionnaire", cascade = { CascadeType.REMOVE,
			CascadeType.MERGE })
	private List<UserQuestionnaire> userQuestionnaires;

	public Questionnaire() {
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

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@JSON(serialize = false)
	@JsonIgnore
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setQuestionnaire(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setQuestionnaire(null);

		return question;
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
		userQuestionnaire.setQuestionnaire(this);

		return userQuestionnaire;
	}

	public UserQuestionnaire removeUserQuestionnaire(
			UserQuestionnaire userQuestionnaire) {
		getUserQuestionnaires().remove(userQuestionnaire);
		userQuestionnaire.setQuestionnaire(null);

		return userQuestionnaire;
	}

}