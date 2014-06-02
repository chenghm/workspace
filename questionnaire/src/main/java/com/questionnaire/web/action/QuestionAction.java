package com.questionnaire.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Question;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.service.IQuestionService;

@Controller
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String id;
	private String oper;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String option6;
	private String questionnaireId;
	private Questionnaire questionnaire;

	@Autowired
	private IQuestionService questionService;

	public String find() {
		return this.refreshGridModel();
	}

	public String edit() {
		try {
			Question question = new Question();
			if ("edit".equals(oper)) {
				question.setId(Integer.parseInt(id));
			}
			question.setName(name);
			question.setOption1(option1);
			question.setOption2(option2);
			question.setOption3(option3);
			question.setOption4(option4);
			question.setOption5(option5);
			question.setOption6(option6);
			question.setType(type);
			questionnaire.setId(Integer.parseInt(questionnaire.getName()));
			question.setQuestionnaire(questionnaire);
			questionService.modify(question);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String delete() {
		try {
			String ids = ((String[]) (ActionContext.getContext()
					.getParameters().get("ids")))[0];
			questionService.delete(Question.class, ids);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	@Override
	public long getResultSize() {
		return questionService.getQuestionCount();
	}

	@Override
	public List<Question> listResults(int from, int length) {
		return questionService.getQuestions(from, length);
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return questionService.getQuestionCount(groupOp, criteria);
	}

	@Override
	public List<Question> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) {
		return questionService.getQuestions(groupOp, criteria, from, length);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
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
