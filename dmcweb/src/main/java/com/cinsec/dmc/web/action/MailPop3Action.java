package com.cinsec.dmc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aspose.email.MailMessage;
import com.cinsec.dmc.dao.impl.Criterion;
import com.cinsec.dmc.entity.MailRecord;
import com.cinsec.dmc.ipparse.IPLocation;
import com.cinsec.dmc.ipparse.IPSeeker;
import com.cinsec.dmc.service.IBaseService;
import com.cinsec.dmc.service.IParameterService;
import com.cinsec.dmc.vo.MailVo;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class MailPop3Action extends BaseAction<MailRecord> {
	private static final long serialVersionUID = 1L;

	private MailRecord result;
	private MailVo vo;
	private List<MailRecord> resultList;

	@Autowired
	@Qualifier("baseService")
	private IBaseService<MailRecord> baseService;
	private String ajaxResult;

	@Autowired
	private IParameterService parameterService;

	public String find() {
		baseService.writeOperLog("查看POP3邮件记录信息");
		return this.refreshGridModel();
	}
	
	public String delete(){
		String productId =  ((String[])(ActionContext.getContext().getParameters().get("productId")))[0];
		baseService.delete(MailRecord.class, productId);
		baseService.writeOperLog("删除POP3邮件记录信息");
		return SUCCESS;
	}

	public String view() {
		baseService.writeOperLog("查看POP3邮件记录信息");
		try {
			Map<String, Object> parameters = ActionContext.getContext()
					.getParameters();
			int id = Integer.parseInt(((String[]) parameters.get("mailId"))[0]);
			result = baseService.getEntity(MailRecord.class, id);

			if (result == null) {
				ajaxResult = "记录不存在";
				return ERROR;
			}
			
			String isRead = result.getIsRead();
			if(!"1".equals(isRead)){
				try {
					baseService.setRecordRead(MailRecord.class, id);
				} catch (Exception e) {
				}
			}

			String webmailpath = parameterService.getParameter("webmailpath");
			vo = new MailVo();
			BeanUtils.copyProperties(vo, result);
//			vo.setBcc(result.getBcc());
//			vo.setCc(result.getCc());
//			vo.setFrom(result.getFrom());
//			vo.setSubject(result.getSubject());
//			vo.setTo(result.getTo());
			IPSeeker ip = new IPSeeker();
			IPLocation	ipLocation = ip.getIPLocation(vo.getSourceIp());
			vo.setSourceBelong(ipLocation.getCountry() + ":"
					+ ipLocation.getArea());
			ipLocation = ip.getIPLocation(vo.getDestIp());
			vo.setDestBelong(ipLocation.getCountry() + ":"
					+ ipLocation.getArea());
			
//			if(!contentFile.exists()){
//				try {
//					vo.setContent(FileUtils.readFileToString(contentFile,"UTF-8"));
//				} catch (Exception e) {
//				}
//			}
			vo.setAttachName(new String[]{});
//			vo.setCookieName("");
			String attachName = result.getAttachName();
			String attachPath = webmailpath + File.separator
					+ result.getId() + File.separator + "attach"
					+ File.separator;
			if (StringUtils.isNotEmpty(attachName)) {
				String[] attachNames = attachName.split(";");
				int length = attachNames.length;
				String[] attachPaths = new String[length];
				
				 for(int i=0;i<length;i++){
					 attachPaths[i]=attachPath+attachNames[i];
				 }
				 vo.setAttachName(attachNames);
				 vo.setAttachPath(attachPaths);
			}
			vo.setContent("");
			String content = result.getContentName();
			if(StringUtils.isNotEmpty(content)){
				vo.setContent(content);
				vo.setContentPath(webmailpath + File.separator
					+ result.getId() + File.separator + content);
				InputStream fis = new FileInputStream(vo.getContentPath());
				MailMessage msg = MailMessage.load(fis);
				vo.setBody((""+msg.getHtmlBody()).replaceFirst("<br><center>This is an evaluation copy of Aspose.Email for Java</center><br><a href=\"http://www.aspose.com/corporate/purchase/end-user-license-agreement.aspx\"><center>View EULA Online</center></a><hr>", ""));
				fis.close();
			}

//			String cookie = result.getCookie();
//			if (StringUtils.isNotEmpty(cookie)) {
//				String cookiePath = webmailpath + File.separator
//						+ result.getId() + File.separator + cookie;
//				vo.setCookiePath(cookiePath);
//				vo.setCookieName(cookie);
//			}

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
		return baseService.getResultCount(MailRecord.class, "classify", 1);
	}

	@Override
	public List<MailRecord> listResults(int from, int length) {
		resultList = baseService.getResultList(MailRecord.class, "classify", 1,
				from, length);
		return resultList;
	}
	
	//解析IP地址
	private List<MailRecord> parseIp(List<MailRecord> list){
		if(CollectionUtils.isEmpty(list)){
			return list;
		}
		IPSeeker ip = new IPSeeker();
		String sourceIp,destIp,sourceBelong,destBelong;
		IPLocation ipLocation;
		String attachName;
		
		for(MailRecord record:list){
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
			attachName = record.getAttachName();
			if(StringUtils.isNotEmpty(attachName)){
				record.setAttachName("1");
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
		return baseService.getResultCount(MailRecord.class, "classify", 1,
				groupOp, criteria);
	}

	@Override
	public List<MailRecord> listResults(String groupOp,
			List<Criterion> criteria, int from, int length) {
		return baseService.getResultList(MailRecord.class, "classify", 1,
				groupOp, criteria, from, length);
	}

	public MailRecord getResult() {
		return result;
	}

	public void setResult(MailRecord result) {
		this.result = result;
	}

	public MailVo getVo() {
		return vo;
	}

	public void setVo(MailVo vo) {
		this.vo = vo;
	}

	private String fileName;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public String download() throws FileNotFoundException {
		Map<String, Object> parameters = ActionContext.getContext()
				.getParameters();
		String attach = ((String[]) parameters.get("attach"))[0];
		File file = new File(attach);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "downloadSuccess";
	}

}
