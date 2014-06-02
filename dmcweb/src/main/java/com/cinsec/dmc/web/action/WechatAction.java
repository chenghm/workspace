package com.cinsec.dmc.web.action;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.ImRecord;
import com.cinsec.dmc.ipparse.IPLocation;
import com.cinsec.dmc.ipparse.IPSeeker;
import com.cinsec.dmc.service.IBaseService;

@Controller
@Scope("prototype")
public class WechatAction extends BaseAction<ImRecord> {
	private static final long serialVersionUID = 1L;

	private ImRecord result;
	private List<ImRecord> resultList;

	@Autowired
	@Qualifier("baseService")
	private IBaseService<ImRecord> baseService;
	private String ajaxResult;

	public String find() {
		baseService.writeOperLog("查看微信记录信息");
		return this.refreshGridModel();
	}

	public String view() {
		baseService.writeOperLog("查看微信记录信息");
		try {
			result = baseService.getEntity(ImRecord.class, result.getId());

			if (result == null) {
				ajaxResult = "记录不存在";
				return ERROR;
			}

			ajaxResult = SUCCESS;
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = "获取记录失败：" + e.getMessage();
			return ERROR;
		}
	}

	@Override
	public long getResultSize() {
		return baseService.getResultCount(ImRecord.class, "imClassify", 5);
	}

	@Override
	public List<ImRecord> listResults(int from, int length) {
		resultList = baseService.getResultList(ImRecord.class, "imClassify", 5,
				from, length);
		return resultList;
	}
	
	private List<ImRecord> parseIp(List<ImRecord> list){
		if(CollectionUtils.isEmpty(list)){
			return list;
		}
		IPSeeker ip = new IPSeeker();
		String sourceIp,destIp,sourceBelong,destBelong;
		IPLocation ipLocation;
		
		for(ImRecord record:list){
			sourceIp = record.getSourceIp();
			if(StringUtils.isNotEmpty(sourceIp)){
				ipLocation = ip.getIPLocation(sourceIp);
				sourceBelong = ipLocation.getCountry() + ":"
						+ ipLocation.getArea();
				record.setSourceIp(sourceIp+"\n"+sourceBelong);
			}
			destIp = record.getDestIp();
			if(StringUtils.isNotEmpty(destIp)){
				ipLocation = ip.getIPLocation(destIp);
				destBelong = ipLocation.getCountry() + ":"
						+ ipLocation.getArea();
				record.setDestIp(destIp+"\n"+destBelong);
			}
			
		}
		
		return list;
	}


	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	@Override
	public long getResultSize(String groupOp, List<Criterion> criteria) {
		return baseService.getResultCount(ImRecord.class, "imClassify", 5,
				groupOp, criteria);
	}

	@Override
	public List<ImRecord> listResults(String groupOp, List<Criterion> criteria,
			int from, int length) {
		return baseService.getResultList(ImRecord.class, "imClassify", 5,
				groupOp, criteria, from, length);
	}

	public ImRecord getResult() {
		return result;
	}

	public void setResult(ImRecord result) {
		this.result = result;
	}

}
