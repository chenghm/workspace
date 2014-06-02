package com.questionnaire.web.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.Questionnaire;
import com.questionnaire.service.IQuestionnaireService;

@Controller
public class QuestionnaireAction extends BaseAction<Questionnaire> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	private String education;
	private Date endtime;
	private String gender;
	private String income;
	private String occupation;
	private int point;
	private int gridTable_id;
	private String id;
	private String oper;
	private List<Questionnaire> list;

	@Autowired
	private IQuestionnaireService questionnaireService;

	public String find() {
		return this.refreshGridModel();
	}
	
	public String findAll(){
		list = questionnaireService.findAll();
		return SUCCESS;
	}

	public String add() {
		try {
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setName(name);
			questionnaire.setAge(age);
			questionnaire.setEducation(education);
			questionnaire.setEndtime(endtime);
			questionnaire.setGender(gender);
			questionnaire.setIncome(income);
			questionnaire.setPoint(point);
			questionnaire.setOccupation(occupation);
			questionnaireService.create(questionnaire);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String edit() {
		try {
			Questionnaire questionnaire = new Questionnaire();
			if("edit".equals(oper)){
				questionnaire.setId(Integer.parseInt(id));
			}
			questionnaire.setName(name);
			questionnaire.setAge(age);
			questionnaire.setEducation(education);
			questionnaire.setEndtime(endtime);
			questionnaire.setGender(gender);
			questionnaire.setIncome(income);
			questionnaire.setPoint(point);
			questionnaire.setOccupation(occupation);
			questionnaireService.modify(questionnaire);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String delete() {
		try {
			String ids =  ((String[])(ActionContext.getContext().getParameters().get("ids")))[0];
			questionnaireService.delete(Questionnaire.class,ids);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	@Override
	public long getResultSize() {
		return questionnaireService.getQuestionnaireCount();
	}

	@Override
	public List<Questionnaire> listResults(int from, int length) {
		return questionnaireService.getQuestionnaires(from, length);
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return questionnaireService.getQuestionnaireCount(groupOp, criteria);
	}

	@Override
	public List<Questionnaire> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return questionnaireService.getQuestionnaires(groupOp, criteria, from,
				length);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public IQuestionnaireService getQuestionnaireService() {
		return questionnaireService;
	}

	public void setQuestionnaireService(
			IQuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}

	public int getGridTable_id() {
		return gridTable_id;
	}

	public void setGridTable_id(int gridTable_id) {
		this.gridTable_id = gridTable_id;
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

	public List<Questionnaire> getList() {
		return list;
	}

	public void setList(List<Questionnaire> list) {
		this.list = list;
	}

}
