package com.cinsec.dmc.web.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public abstract class CopyOfBaseAction<T> extends ActionSupport {

	private static final long serialVersionUID = 1L;
	// 和jqGrid组件相关的参数属性
	private List<T> gridModel = Collections.emptyList();
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Long record = 0l;
	private String sord;
	private String sidx;
	private String search;
	protected Map<String, String> messages = new HashMap<String, String>(); 

	public abstract long getResultSize();

	public abstract List<T> listResults(int from, int length);

	public String refreshGridModel() {
		try {
			List<T> results = Collections.emptyList();
			record = this.getResultSize();
			int from = rows * (page - 1);
			int length = rows;
			results = this.listResults(from, length);
			this.setGridModel(results);
			total = (int) Math.ceil((double) record / (double) rows);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public List<T> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<T> gridModel) {
		this.gridModel = gridModel;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Long getRecord() {
		return record;
	}

	public void setRecord(Long record) {
		this.record = record;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

}
