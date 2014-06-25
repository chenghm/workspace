package com.cinsec.dmc.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.service.IGeneralService;
import com.cinsec.dmc.vo.GeneralVo;
@Controller
@Scope("prototype")
public class GeneralAction extends BaseAction<GeneralVo> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IGeneralService generalService;
	
	public String find(){
		 return this.refreshGridModel();  
	}
	
	@Override
	public long getResultSize() {
		return generalService.getResultSize();
	}
	@Override
	public List<GeneralVo> listResults(int from, int length) {
		return generalService.listResults(from,length);
	}
	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return generalService.getResultSize(groupOp,criteria);
	}
	@Override
	public List<GeneralVo> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return generalService.listResults(groupOp,criteria,from,length);
	}

}
