package com.questionnaire.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the user_question database table.
 */
@Entity
@Table(name = "T_USER_QUESTION")
//@SequenceGenerator(name = "SEQ_USER_QUESTION", sequenceName = "SEQ_USER_QUESTION")
public class UserQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_USER_QUESTION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String answer;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    // bi-directional many-to-one association to Questionnaire
    @ManyToOne
    private Question question;


    public UserQuestion() {
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}