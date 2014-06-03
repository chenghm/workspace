package com.questionnaire.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
@Table(name="T_QUESTION")
//@SequenceGenerator(name = "SEQ_QUESTION", sequenceName = "SEQ_QUESTION")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_QUESTION")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	private String option1;

	private String option2;

	private String option3;

	private String option4;
	
	private String option5;
	
	private String option6;

	private String type;

	// bi-directional many-to-one association to Questionnaire
	@ManyToOne
	private Questionnaire questionnaire;

	// bi-directional many-to-one association to UserQuestionnaire
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<UserQuestion> userQuestions;

	public Question() {
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

	public String getOption1() {
		return this.option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return this.option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return this.option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return this.option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	@JSON(serialize=false)
	@JsonIgnore
	public List<UserQuestion> getUserQuestions() {
		return userQuestions;
	}

	public void setUserQuestions(List<UserQuestion> userQuestions) {
		this.userQuestions = userQuestions;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public String getOption6() {
		return option6;
	}

	public void setOption6(String option6) {
		this.option6 = option6;
	}

}