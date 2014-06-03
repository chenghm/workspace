package com.questionnaire.dao.impl;

import com.questionnaire.dao.IManagerDao;
import com.questionnaire.entity.Manager;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: Class XXXX Description:
 *
 * @author chenghongming
 * @version xxx
 */
@Repository
public class ManagerDao extends BaseDao<Manager> implements IManagerDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    public Manager findUserByUsername(String username) throws SQLException {
        String jql = "from Manager u where u.username =:username";
        Map<String, Object> parVals = new HashMap<String, Object>(1);
        parVals.put("username", username);
        List<Manager> users = (List<Manager>) this.getEntityList(jql, parVals);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

}
