package com.cinsec.dmc.dao;

import java.sql.SQLException;
import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.vo.GeneralVo;

public interface IGeneralDao extends IBaseDao<GeneralVo> {

	long getResultSize() throws SQLException;

	List<GeneralVo> listResults(int from, int length) throws SQLException;

	long getResultSize(String groupOp, List<Criterion> criteria) throws SQLException;

	List<GeneralVo> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) throws SQLException;

}
