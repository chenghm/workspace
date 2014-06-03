package com.questionnaire.dao;

import com.questionnaire.dao.impl.Criterion;
import com.questionnaire.entity.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User> {

    long getUsersCount();

    List<User> getUsers(int pageNo, int pageSize);

    int createUser(User user) throws Exception;

    long getUsersCount(String groupOp, List<Criterion> criteria) throws Exception;

    List<User> getUsers(String groupOp, List<Criterion> criteria, int from, int length) throws Exception;

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);


}
