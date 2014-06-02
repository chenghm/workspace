package com.questionnaire.service;

import java.util.List;

import com.questionnaire.dao.impl.Criterion;




public interface IBaseService<T> {

	public T getEntity(int id);

	public void create(T obj);

	public void modify(T obj);

	public void delete(String ids);

	public Long getPages();

	T getEntity(Class<T> clazz, int id);

	List<T> getEntityList(Class<T> clazz, int pageNo);

	List<T> getEntityList(T obj, int pageNo);

	Long getPages(Class<T> clazz);

	Long getPages(T obj);

	void delete(Class<T> clazz, String dataIds);

	boolean isAssociated(String dataIds);

	List<T> getEntityList(Class<T> clazz);

	Long getPages(int pageSize);

	Long getPages(Class<T> clazz, int pageSize);

	Long getPages(T obj, int pageSize);

	boolean isDuplicated(Class<?> clazz, String propertyName,
			Object propertyValue);

	List<?> findEntityByProperty(Class<?> entityClass, String propertyName,
			Object propertyValue);

	Long getEntityCount(Class<T> clazz);

	List<T> getEntityList(Class<T> clazz, int from, int length);

	long getResultCount(Class<T> clazz, String groupOp, List<Criterion> criteria);

	List<T> getResultList(Class<T> clazz, String groupOp,
			List<Criterion> criteria, int from, int length);

	Long getResultCount(Class<T> entityClass, String propertyName,
			Object propertyValue);

	List<T> getResultList(Class<T> entityClass, String propertyName,
			Object propertyValue, int from, int length);

	Long getResultCount(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria);

	List<T> getResultList(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria,
			int from, int length);

	long getTheDayCount(Class<T> clazz, String propertyName,
			Object propertyValue);
	
	
	void setRecordRead(Class<T> clazz,int id);
}
