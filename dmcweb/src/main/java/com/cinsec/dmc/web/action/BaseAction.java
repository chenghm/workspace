package com.cinsec.dmc.web.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.dao.impl.Criterion.CompareType;
import com.cinsec.dmc.entity.MailRecord;
import com.cinsec.dmc.ipparse.IPLocation;
import com.cinsec.dmc.ipparse.IPSeeker;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction<T> extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> gridModel = Collections.emptyList();
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Long record = 0l;
	private String sord;
	private String sidx;

	// (1)添加和查询有关的成员变量search、searchField、searchString、searchOper
	private boolean search;
	private String searchField;
	private String searchString;
	private String searchOper;
	private String searchType;

	public abstract long getResultSize();

	public abstract List<T> listResults(int from, int length);

	private String filters;

	// (2)添加用于根据条件进行查询的方法
	public abstract long getResultSize(String groupOp, List<Criterion> criteria);

	public abstract List<T> listResults(String groupOp,
			List<Criterion> criteria, int from, int length);

	protected Map<String, Object> messages = new HashMap<String, Object>();

	public String refreshGridModel() {
		try {
			List<Criterion> criteria = Collections.emptyList();

			if (search == true) {
				criteria = new ArrayList<Criterion>();

				// (2)将Filter转化为Criterion列表，并加入总的Criterion列表
				if (filters != null && filters.length() > 0) {
					criteria.addAll(this
							.generateSearchCriteriaFromFilters(filters));
				}

				// (3)将searchField、searchString、searchOper转化为Criterion，并加入总的Criterion列表
				Criterion criterion = this.generateSearchCriterion(searchField,
						searchString, searchOper);
				if (criterion != null) {
					criteria.add(criterion);
				}
			}

			int from = rows * (page - 1);
			int length = rows;

			List<T> results = Collections.emptyList();

			if (!criteria.isEmpty()) {
				String groupOp = JSONObject.fromObject(filters).getString(
						"groupOp");

				record = this.getResultSize(groupOp, criteria);
				results = this.listResults(groupOp, criteria, from, length);

			} else {
				record = this.getResultSize();
				results = this.listResults(from, length);

			}

			this.setGridModel(parseIp(results));
			total = (int) Math.ceil((double) record / (double) rows);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	// 解析IP地址
	private List<T> parseIp(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		IPSeeker ip = new IPSeeker();
		String sourceIp, destIp, sourceBelong, destBelong, attachName;
		IPLocation ipLocation;
		Field field;

		try {
			for (T record : list) {
				field = record.getClass().getDeclaredField("sourceIp");
				field.setAccessible(true);
				sourceIp = (String) field.get(record);
				if (StringUtils.isNotEmpty(sourceIp)) {
					ipLocation = ip.getIPLocation(sourceIp);
					sourceBelong = ipLocation.getCountry() + ":"
							+ ipLocation.getArea();

					field.set(record, sourceIp + "\n" + sourceBelong);
				}
				field = record.getClass().getDeclaredField("destIp");
				field.setAccessible(true);
				destIp = (String) field.get(record);
				if (StringUtils.isNotEmpty(destIp)) {
					ipLocation = ip.getIPLocation(destIp);
					destBelong = ipLocation.getCountry() + ":"
							+ ipLocation.getArea();
					field.set(record, destIp + "\n" + destBelong);
				}
				if (record instanceof MailRecord) {
					field = record.getClass().getDeclaredField("attachName");
					field.setAccessible(true);
					attachName = (String) field.get(record);
					if (StringUtils.isNotEmpty(attachName)) {
						field.set(record, "1");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// (6)通过searchField、searchString、searchOper三个参数生成Criterion的方法
	public Criterion generateSearchCriterion(String searchField,
			Object searchString, String searchOper) {
		Criterion criterion = null;

		// (7)如果searchField、searchString、searchOper均不为null，且searchString不为空字符串时，则创建Criterion
		if (searchField != null && searchString != null
		/* & searchString.length() > 0 */&& searchOper != null) {
			if ("eq".equals(searchOper)) {
				criterion = Criterion.getEqualCriterion(searchField,
						searchString, null);
			} else if ("ne".equals(searchOper)) {
				criterion = Criterion.getCompareCriterion(CompareType.NE,
						searchField, searchString, null);
			} else if ("lt".equals(searchOper)) {
				criterion = Criterion.getCompareCriterion(CompareType.LT,
						searchField, searchString, null);
			} else if ("le".equals(searchOper)) {
				criterion = Criterion.getCompareCriterion(CompareType.LTE,
						searchField, searchString, null);
			} else if ("gt".equals(searchOper)) {
				criterion = Criterion.getCompareCriterion(CompareType.GT,
						searchField, searchString, null);
			} else if ("ge".equals(searchOper)) {
				criterion = Criterion.getCompareCriterion(CompareType.GTE,
						searchField, searchString, null);
			} else if ("bw".equals(searchOper)) {
				criterion = Criterion.getLikeCriterion(searchField,
						searchString + "%", null);
			} else if ("bn".equals(searchOper)) {
				criterion = Criterion.getNotLikeCriterion(searchField,
						searchString + "%", null);
			} else if ("ew".equals(searchOper)) {
				criterion = Criterion.getLikeCriterion(searchField, "%"
						+ searchString, null);
			} else if ("en".equals(searchOper)) {
				criterion = Criterion.getNotLikeCriterion(searchField, "%"
						+ searchString, null);
			} else if ("cn".equals(searchOper)) {
				criterion = Criterion.getLikeCriterion(searchField, "%"
						+ searchString + "%", null);
			} else if ("nc".equals(searchOper)) {
				criterion = Criterion.getNotLikeCriterion(searchField, "%"
						+ searchString + "%", null);
			}
		}
		return criterion;
	}

	@SuppressWarnings("unchecked")
	public List<Criterion> generateSearchCriteriaFromFilters(String filters) {
		List<Criterion> criteria = new ArrayList<Criterion>();

		JSONObject jsonObject = JSONObject.fromObject(filters);

		JSONArray rules = jsonObject.getJSONArray("rules");
		Iterator<JSONObject> it = rules.iterator();
		JSONObject rule;
		while (it.hasNext()) {
			rule = it.next();
			String field = rule.getString("field");
			String op = rule.getString("op");
			Object data = rule.get("data");

			Criterion criterion = this.generateSearchCriterion(field, data, op);

			if (criterion != null) {
				criteria.add(criterion);
			}
		}

		return criteria;
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

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public Map<String, Object> getMessages() {
		return messages;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	protected  String getFileCharset(File sourceFile) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null ;
        try {
         //boolean checked = false;
        
          bis = new BufferedInputStream(
           new FileInputStream(sourceFile));
         bis.mark(0);
        
         int read = bis.read( first3Bytes, 0, 3);
        
         if (read == -1) {
          return charset; //文件编码为 ANSI
         } else if ( first3Bytes[0] == (byte) 0xFF
           && first3Bytes[1] == (byte) 0xFE) {
         
          charset = "UTF-16LE"; //文件编码为 Unicode
          //checked = true;
         } else if ( first3Bytes[0] == (byte) 0xFE
           && first3Bytes[1] == (byte) 0xFF) {
         
          charset = "UTF-16BE"; //文件编码为 Unicode big endian
          //checked = true;
         } else if ( first3Bytes[0] == (byte) 0xEF
           && first3Bytes[1] == (byte) 0xBB
           && first3Bytes[2] == (byte) 0xBF) {
         
          charset = "UTF-8"; //文件编码为 UTF-8
          //checked = true;
         }
         bis.reset();
        
         

         bis.close();
        } catch (Exception e) {
         e.printStackTrace();
        }finally{
        	if(bis!=null){
        		try {
					bis.close();
				} catch (IOException e) {
				}
        	}
        }

        return charset;
       }


}
