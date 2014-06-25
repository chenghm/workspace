package com.cinsec.dmc.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinsec.dmc.dao.IGeneralDao;
import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.exception.SysException;
import com.cinsec.dmc.service.IGeneralService;
import com.cinsec.dmc.vo.GeneralVo;
@Service
public class GeneralService  extends BaseService<GeneralVo> implements IGeneralService{
	private static final long serialVersionUID = -1940493766857032987L;
	private static final Logger logger = Logger.getLogger(GeneralService.class);

	@Autowired
	private IGeneralDao generalDao;
	@Override
	public long getResultSize() {
		try {
			return generalDao.getResultSize();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public List<GeneralVo> listResults(int from, int length) {
		try {
			return generalDao.listResults(from,length);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		try {
			return generalDao.getResultSize(groupOp,criteria);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new SysException(e);
		}
	}

	@Override
	public List<GeneralVo> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		try {
			return generalDao.listResults(groupOp,criteria,from,length);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
			throw new SysException(e);
		}
	}

}
