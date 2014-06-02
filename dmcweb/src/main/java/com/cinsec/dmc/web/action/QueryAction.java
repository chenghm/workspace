package com.cinsec.dmc.web.action;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.ipparse.IPLocation;
import com.cinsec.dmc.ipparse.IPSeeker;
import com.cinsec.dmc.service.IBaseService;
import com.opensymphony.xwork2.ActionContext;

public class QueryAction<T> extends BaseAction<T> {
	private static final long serialVersionUID = 1L;

	private Class<T> entityClass;
	private T result;
	private List<T> resultList;
	protected String logSubject;

	@Autowired
	@Qualifier("baseService")
	private IBaseService<T> baseService;
	private String ajaxResult;

	@SuppressWarnings("unchecked")
	public QueryAction() {
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	public String find() {
		baseService.writeOperLog("查看" + logSubject + "信息");
		return this.refreshGridModel();
	}
	
	public String delete(){
		String productId =  ((String[])(ActionContext.getContext().getParameters().get("productId")))[0];
		baseService.delete(entityClass, productId);
		baseService.writeOperLog("删除"+logSubject+"记录信息");
		return SUCCESS;
	}


	public String view() {
		baseService.writeOperLog("查看" + logSubject + "信息");
		try {
			int id = entityClass.getDeclaredField("id").getInt(result);
			result = baseService.getEntity(entityClass, id);

			if (result == null) {
				ajaxResult = "记录不存在";
				return ERROR;
			}

			ajaxResult = SUCCESS;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = "获取记录失败：" + e.getMessage();
			return ERROR;
		}
	}

	@Override
	public long getResultSize() {
		return baseService.getEntityCount(entityClass);
	}

	@Override
	public List<T> listResults(int from, int length) {
		resultList = baseService.getEntityList(entityClass, from, length);
		return parseIp(resultList);
	}

	// 解析IP地址
	private List<T> parseIp(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		IPSeeker ip = new IPSeeker();
		String sourceIp, destIp, sourceBelong, destBelong;
		IPLocation ipLocation;
		Field field;

		try {
			for (T record : list) {
				field  = record.getClass().getDeclaredField("sourceIp");
				field.setAccessible(true);
				sourceIp = (String) field.get(record);
				if (StringUtils.isNotEmpty(sourceIp)) {
					ipLocation = ip.getIPLocation(sourceIp);
					sourceBelong = ipLocation.getCountry() + ":"
							+ ipLocation.getArea();

					field = record.getClass().getDeclaredField("sourceBelong");
					field.setAccessible(true);
					field.set(record, sourceBelong);
				}
				field  = record.getClass().getDeclaredField("destIp");
				field.setAccessible(true);
				destIp = (String) field.get(record);
				if (StringUtils.isNotEmpty(destIp)) {
					ipLocation = ip.getIPLocation(destIp);
					destBelong = ipLocation.getCountry() + ":"
							+ ipLocation.getArea();
					field = record.getClass().getDeclaredField("destBelong");
					field.setAccessible(true);
					field.set(record, destBelong);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return baseService.getResultCount(entityClass, groupOp, criteria);
	}

	@Override
	public List<T> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) {
		return baseService.getResultList(entityClass, groupOp, criteria, from,
				length);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

}
