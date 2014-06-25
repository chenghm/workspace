package com.cinsec.dmc.service;

import java.util.List;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.vo.GeneralVo;

public interface IGeneralService extends IBaseService<GeneralVo>{

	long getResultSize();

	List<GeneralVo> listResults(int from, int length);

	long getResultSize(String groupOp, List<Criterion> criteria);

	List<GeneralVo> listResults(String groupOp, List<Criterion> criteria,
			int from, int length);

}
