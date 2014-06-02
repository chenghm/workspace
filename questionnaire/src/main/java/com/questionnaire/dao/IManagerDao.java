package com.questionnaire.dao;

import java.sql.SQLException;

import com.questionnaire.entity.Manager;

public interface IManagerDao extends IBaseDao<Manager>{

	Manager findUserByUsername(String username) throws SQLException;

}
