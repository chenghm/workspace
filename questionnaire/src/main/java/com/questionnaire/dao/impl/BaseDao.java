package com.questionnaire.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.questionnaire.dao.IBaseDao;

@Repository
public class BaseDao<T extends Object> implements IBaseDao<T>, Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "MYSQLPU")
	protected EntityManager entityManager;
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	@Override
	public T getEntity(int id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public T getEntity(Class<T> clazz, int id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public int executeJpl(String jql) {
		return entityManager.createQuery(jql).executeUpdate();

	}

	@Override
	public int executeJpl(String jql, Map<String, Object> parameters) {
		Query query = entityManager.createQuery(jql);
		if (MapUtils.isNotEmpty(parameters)) {
			Iterator<String> iterator = parameters.keySet().iterator();
			String key = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		return query.executeUpdate();
	}

	@Override
	public List<?> findEntityByProperty(Class<?> entityClass,
			String propertyName, Object propertyValue) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> criteriaQuery = criteriaBuilder
				.createQuery(entityClass);
		Root<?> root = criteriaQuery.from(entityClass);
		Predicate predicate = criteriaBuilder.equal(root.get(propertyName),
				propertyValue);
		criteriaQuery.where(predicate);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Long getResultCount(Class<T> entityClass, String propertyName,
			Object propertyValue) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(criteriaBuilder.count(root));
		Predicate predicate = criteriaBuilder.equal(root.get(propertyName),
				propertyValue);
		criteriaQuery.where(predicate);
		Query query = entityManager.createQuery(criteriaQuery);
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getResultList(Class<T> entityClass, String propertyName,
			Object propertyValue, int from, int length) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		Predicate predicate = criteriaBuilder.equal(root.get(propertyName),
				propertyValue);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		Query query = entityManager.createQuery(criteriaQuery)
				.setFirstResult(from).setMaxResults(length);
		return query.getResultList();

	}

	@Override
	public List<T> getEntityList(String jql, int pageNo) {
		return getEntityList(jql, null, pageNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntityList(int from, int length) {
		Query query = entityManager
				.createQuery(" from :entity t order by t.updatedTime desc");
		query.setParameter("entity", entityClass.getName());
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntityList(Class<T> clazz) {

		StringBuffer jql = new StringBuffer();
		jql.append(" from ").append(clazz.getSimpleName());
		Query query = entityManager.createQuery(jql.toString());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntityList(Class<T> clazz, int from, int length) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		Field[] fields = clazz.getDeclaredFields();
		Path<Object> path = null;
		for (Field field : fields) {
			if ("updatedTime".equals(field.getName())) {
				path = root.get("updatedTime");
				break;
			}
			if("time".equals(field.getName())){
				path = root.get("time");
				break;
			}
		}
		if (path == null) {
			path = root.get("id");
		}

		criteriaQuery.orderBy(criteriaBuilder.desc(path));
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntityList(T obj, int pageNo, int pageSize)
			throws SQLException {
		Query query;
		try {
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			Class<T> clazz = (Class<T>) obj.getClass();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
			Root<T> root = criteriaQuery.from(clazz);
			Field[] fileds = clazz.getDeclaredFields();
			Predicate predicate = null;
			Object value = null;
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Field field : fileds) {
				field.setAccessible(true);
				value = field.get(obj);
				if (!Modifier.isStatic(field.getModifiers()) && value != null) {
					if (value instanceof String) {
						predicate = criteriaBuilder.like(
								root.<String> get(field.getName()), "%" + value
										+ "%");
					} else {
						predicate = criteriaBuilder.equal(
								root.get(field.getName()), value);
					}

					predicates.add(predicate);

				}
			}
			criteriaQuery.where(criteriaBuilder.and(predicates
					.toArray(new Predicate[0])));
			criteriaQuery
					.orderBy(criteriaBuilder.desc(root.get("updatedTime")));
			query = entityManager.createQuery(criteriaQuery);

			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(
					pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getEntityList(String jql,
			Map<String, ? extends Object> parameters, int from, int length) {
		Query query = entityManager.createQuery(jql);
		if (MapUtils.isNotEmpty(parameters)) {
			Iterator<String> iterator = parameters.keySet().iterator();
			String key = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public Long getEntityCount() {
		Query query = entityManager.createQuery(" select count(t) from "
				+ this.entityClass.getName() + " t");
		// query.setParameter("entity", this.entityClass.getName());
		return ((Long) query.getSingleResult()).longValue();
	}

	@Override
	public Long getEntityCount(Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(criteriaBuilder.count(root));
		Query query = entityManager.createQuery(criteriaQuery);
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getEntityCount(T obj) throws SQLException {
		Query query;
		try {
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			Class<T> clazz = (Class<T>) obj.getClass();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder
					.createQuery(Long.class);
			Root<T> root = criteriaQuery.from(clazz);
			criteriaQuery.select(criteriaBuilder.count(root));
			Field[] fileds = clazz.getDeclaredFields();
			Predicate predicate = null;
			Object value = null;
			List<Predicate> predicates = new ArrayList<Predicate>();
			for (Field field : fileds) {
				field.setAccessible(true);
				value = field.get(obj);
				if (!Modifier.isStatic(field.getModifiers()) && value != null) {
					if (value instanceof String) {
						predicate = criteriaBuilder.like(
								root.<String> get(field.getName()), "%" + value
										+ "%");
					} else {
						predicate = criteriaBuilder.equal(
								root.get(field.getName()), value);
					}

					predicates.add(predicate);

				}
			}

			criteriaQuery.where(criteriaBuilder.and(predicates
					.toArray(new Predicate[0])));
			query = entityManager.createQuery(criteriaQuery);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		return (Long) query.getSingleResult();
	}

	@Override
	public T create(T obj) throws Exception {
		entityManager.persist(obj);
		return obj;
	}

	@Override
	public void createList(List<T> list) {
		for (T obj : list) {
			entityManager.persist(obj);
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		T obj = entityManager.find(entityClass, id);
		entityManager.remove(obj);

	}

	@Override
	public void delete(Class<T> clazz, int id) {
		T obj = entityManager.find(clazz, id);
		entityManager.remove(obj);
	}

	@Override
	public void modify(T obj) throws SQLException {
		entityManager.merge(obj);
	}

	@Override
	public Long getEntityCount(String jql,
			Map<String, ? extends Object> parameters) {
		Query query = entityManager.createQuery(jql);

		if (MapUtils.isNotEmpty(parameters)) {
			Iterator<String> iterator = parameters.keySet().iterator();
			String key = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		return ((Long) query.getSingleResult()).longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Object> getEntityList(String jql,
			Map<String, ? extends Object> parameters) {
		Query query = entityManager.createQuery(jql);
		if (MapUtils.isNotEmpty(parameters)) {
			Iterator<String> iterator = parameters.keySet().iterator();
			String key = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				query.setParameter(key, parameters.get(key));
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Object> getEntityList(String jql) {
		Query query = entityManager.createQuery(jql);
		return query.getResultList();
	}

	@Override
	public List<T> getEntityList(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getEntityList(String jql,
			Map<String, ? extends Object> parameters, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getEntityList(Class<T> clazz, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getEntityList(T obj, int pageNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public long getResultCount(Class<T> clazz, String groupOp,
			List<Criterion> criteria) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(clazz, criteria);

		String jql = " select count(t) from " + clazz.getSimpleName()
				+ " t where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			paVals.get(i).getClass();
			query.setParameter(i + 1, paVals.get(i));
		}
		return (long) query.getResultList().get(0);
	}

	@Override
	public Long getResultCount(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria)
			throws Exception {

		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(clazz, criteria);
		StringBuffer jql = new StringBuffer();
		jql.append(" select count(t) from ").append(clazz.getSimpleName())
				.append(" t where ").append(propertyName).append(" = ")
				.append(propertyValue).append(" and ").append(sql);
		Query query = this.getEntityManager().createQuery(jql.toString());
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			paVals.get(i).getClass();
			query.setParameter(i + 1, paVals.get(i));
		}
		return (long) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getResultList(Class<T> clazz, String propertyName,
			Object propertyValue, String groupOp, List<Criterion> criteria,
			int from, int length) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(clazz, criteria);
		StringBuffer jql = new StringBuffer();
		jql.append(" from ").append(clazz.getSimpleName()).append(" t where ")
				.append(propertyName).append(" = ").append(propertyValue)
				.append(" and ").append(sql).append(" order by id desc");
		Query query = this.getEntityManager().createQuery(jql.toString());
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			query.setParameter(i + 1, paVals.get(i));
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getResultList(Class<T> clazz, String groupOp,
			List<Criterion> criteria, int from, int length) throws Exception {
		String sql = Criterion.convertToSql(groupOp, criteria);
		List<Object> paVals = Criterion.getCriteriaValues(clazz, criteria);

		String jql = " from " + clazz.getSimpleName() + " t where " + sql;
		Query query = this.getEntityManager().createQuery(jql);
		int size = paVals.size();
		for (int i = 0; i < size; i++) {
			query.setParameter(i + 1, paVals.get(i));
		}
		query.setFirstResult(from).setMaxResults(length);
		return query.getResultList();
	}
	
	@Override
	public long getTheDayCount(Class<T> clazz ,String propertyName,Object propertyValue){
		StringBuffer hql = new StringBuffer(" select count(t) from ");
		hql.append(clazz.getName()).append(" t where to_days(t.time) = to_days(now()) ");
		if(StringUtils.isNotEmpty(propertyName)&& propertyValue!=null){
		hql.append(" and t.").append(propertyName).append("=").append(propertyValue);
		}
		Query query = entityManager.createQuery(hql.toString());
		return (long) query.getResultList().get(0);
	}

	@Override
	public void setRecordRead(Class<T> clazz,int id){
		StringBuffer hql = new StringBuffer(" update ");
		hql.append(clazz.getName()).append( " set isRead='1' where id= ").append(id);
		entityManager.createQuery(hql.toString()).executeUpdate();
	}
	/*
	 * public Class<T> getEntityClass() { Type t =
	 * getClass().getGenericSuperclass(); if (t instanceof ParameterizedType) {
	 * Type[] p = ((ParameterizedType) t).getActualTypeArguments();
	 * this.entityClass = (Class<T>) p[0]; } return entityClass; }
	 */
}