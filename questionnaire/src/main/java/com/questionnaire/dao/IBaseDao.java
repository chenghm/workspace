package com.questionnaire.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.questionnaire.dao.impl.Criterion;

public interface IBaseDao<T> {

	T getEntity(int id);

	List<T> getEntityList(String jql, int pageNo);

	Long getEntityCount();

	Long getEntityCount(String jql, Map<String, ? extends Object> parameters);

	T create(T obj) throws  Exception;

	void delete(int id) throws SQLException;

	void modify(T obj) throws SQLException;

	List<T> getEntityList(int pageNo);

	List<T> getEntityList(String jql, Map<String, ? extends Object> parameters,
			int pageNo);

	List<? extends Object> getEntityList(String jql,
			Map<String, ? extends Object> parameters);

	List<T> getEntityList(Class<T> clazz, int pageNo);

	List<T> getEntityList(T obj, int pageNo) throws SQLException;

	Long getEntityCount(Class<T> clazz);

	Long getEntityCount(T obj) throws SQLException;

	void delete(Class<T> clazz, int id);

	T getEntity(Class<T> clazz, int id);

	List<?> findEntityByProperty(Class<?> entityClass, String propertyName,
			Object propertyValue);

	List<T> getEntityList(Class<T> clazz);

	void createList(List<T> list);

	int executeJpl(String jql);

	int executeJpl(String jql, Map<String, Object> parameters);

	List<T> getEntityList(int pageNo, int pageSize);

	List<T> getEntityList(Class<T> clazz, int pageNo, int pageSize);

	List<T> getEntityList(T obj, int pageNo, int pageSize) throws SQLException;

	List<? extends Object> getEntityList(String jql);
	
	long getResultCount(Class<T> clazz,String groupOp,List<Criterion> criteria) throws Exception;

	List<T> getResultList(Class<T> clazz,String groupOp,List<Criterion> criteria, int from, int length) throws Exception;

	Long getResultCount(Class<T> entityClass, String propertyName,
			Object propertyValue);

	List<T> getResultList(Class<T> entityClass, String propertyName,
			Object propertyValue, int from, int length);

	Long getResultCount(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria)
			throws Exception;

	List<T> getResultList(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria,
			int from, int length) throws Exception;


	long getTheDayCount(Class<T> clazz, String propertyName,
			Object propertyValue);

	void setRecordRead(Class<T> clazz, int id);

}
