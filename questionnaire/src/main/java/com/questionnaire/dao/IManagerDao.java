package com.questionnaire.dao;

import com.questionnaire.entity.Manager;

import java.sql.SQLException;

public interface IManagerDao extends IBaseDao<Manager> {

    Manager findUserByUsername(String username) throws SQLException;

}
