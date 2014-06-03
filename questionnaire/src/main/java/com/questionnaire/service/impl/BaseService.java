package com.questionnaire.service.impl;

import com.questionnaire.dao.IBaseDao;
import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.exception.SysException;
import com.questionnaire.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Service
public class BaseService<T extends Object> implements IBaseService<T>,
        Serializable {

    private static final long serialVersionUID = 6756209460051701975L;

    @Autowired
    @Qualifier("baseDao")
    private IBaseDao<T> baseDao;

    // private Validation

    @Override
    public T getEntity(int id) {
        return baseDao.getEntity(id);
    }

    @Override
    @Transactional
    public void create(T obj) {
        try {
            baseDao.create(obj);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    @Transactional
    public void modify(T obj) {
        try {
            baseDao.modify(obj);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    @Transactional
    public void delete(String dataIds) {
        try {
            String[] ids = dataIds.split(",");
            for (String id : ids) {
                baseDao.delete(Integer.valueOf(id));
            }
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    @Transactional
    public void delete(Class<T> clazz, String dataIds) {
        String[] ids = dataIds.split(",");
        for (String id : ids) {
            baseDao.delete(clazz, Integer.valueOf(id));
        }
    }

    @Override
    public Long getPages(int pageSize) {
        Long total = baseDao.getEntityCount();
        return (total - 1) / pageSize + 1;
    }

    @Override
    public Long getPages(Class<T> clazz, int pageSize) {
        Long total = baseDao.getEntityCount(clazz);
        return (total - 1) / pageSize + 1;
    }

    @Override
    public Long getPages(T obj, int pageSize) {
        try {
            Long total = baseDao.getEntityCount(obj);
            return (total - 1) / pageSize + 1;
        } catch (SQLException e) {
            throw new SysException(e);
        }
    }

    @Override
    public T getEntity(Class<T> clazz, int id) {
        return baseDao.getEntity(clazz, id);
    }

    @Override
    public List<T> getEntityList(Class<T> clazz, int pageNo) {
        return baseDao.getEntityList(clazz, pageNo);
    }

    @Override
    public List<T> getEntityList(T obj, int pageNo) {
        try {
            return baseDao.getEntityList(obj, pageNo);
        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public boolean isAssociated(String dataIds) {
        return false;
    }


    @Override
    public List<T> getEntityList(Class<T> clazz) {
        return baseDao.getEntityList(clazz);

    }

    @Override
    public Long getPages() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long getPages(Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long getPages(T obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDuplicated(Class<?> clazz, String propertyName,
                                Object propertyValue) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<?> findEntityByProperty(Class<?> entityClass,
                                        String propertyName, Object propertyValue) {
        return baseDao.findEntityByProperty(entityClass, propertyName,
                propertyValue);
    }

    @Override
    public Long getEntityCount(Class<T> clazz) {
        return baseDao.getEntityCount(clazz);
    }

    @Override
    public List<T> getEntityList(Class<T> clazz, int from, int length) {
        return baseDao.getEntityList(clazz, from, length);
    }

    @Override
    public long getResultCount(Class<T> clazz, String groupOp,
                               List<Criterion> criteria) {
        try {
            return baseDao.getResultCount(clazz, groupOp, criteria);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    public List<T> getResultList(Class<T> clazz, String groupOp,
                                 List<Criterion> criteria, int from, int length) {
        try {
            return baseDao
                    .getResultList(clazz, groupOp, criteria, from, length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    public Long getResultCount(Class<T> entityClass, String propertyName,
                               Object propertyValue) {
        return baseDao.getResultCount(entityClass, propertyName, propertyValue);
    }

    @Override
    public List<T> getResultList(Class<T> entityClass, String propertyName,
                                 Object propertyValue, int from, int length) {
        return baseDao.getResultList(entityClass, propertyName, propertyValue,
                from, length);
    }

    @Override
    public Long getResultCount(Class<T> clazz, String propertyName,
                               Object propertyValue, String groupOp, List<Criterion> criteria) {
        try {
            return baseDao.getResultCount(clazz, propertyName, propertyValue,
                    groupOp, criteria);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    public List<T> getResultList(Class<T> clazz, String propertyName,
                                 Object propertyValue, String groupOp, List<Criterion> criteria,
                                 int from, int length) {
        try {
            return baseDao.getResultList(clazz, propertyName, propertyValue,
                    groupOp, criteria, from, length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException(e);
        }
    }

    @Override
    public long getTheDayCount(Class<T> clazz, String propertyName,
                               Object propertyValue) {
        return baseDao.getTheDayCount(clazz, propertyName, propertyValue);
    }

    @Override
    @Transactional
    public void setRecordRead(Class<T> clazz, int id) {
        baseDao.setRecordRead(clazz, id);

    }

}
