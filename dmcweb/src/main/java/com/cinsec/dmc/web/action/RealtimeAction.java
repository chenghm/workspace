package com.cinsec.dmc.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.entity.CommerceRecord;
import com.cinsec.dmc.entity.ControlRecord;
import com.cinsec.dmc.entity.FtpRecord;
import com.cinsec.dmc.entity.HttpRecord;
import com.cinsec.dmc.entity.ImRecord;
import com.cinsec.dmc.entity.MailRecord;
import com.cinsec.dmc.entity.NetdiskRecord;
import com.cinsec.dmc.entity.PasswordRecord;
import com.cinsec.dmc.entity.SearchRecord;
import com.cinsec.dmc.entity.SocialRecord;
import com.cinsec.dmc.entity.ThroughRecord;
import com.cinsec.dmc.service.IBaseService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class RealtimeAction extends ActionSupport {
	@SuppressWarnings("rawtypes")
	@Autowired
	@Qualifier("baseService")
	private IBaseService baseService;

	private static final long serialVersionUID = 3468819030219210296L;
	private long httpCount;
	private long searchCount;
	private long throughCount;
	private long commerceCount;
	private long socialCount;
	private long passwordCount;
	private long ftpCount;
	private long netdiskCount;
	private long controlCount;
	private long msnCount;
	private long qqCount;
	private long fetionCount;
	private long wechatCount;
	private long skypeCount;
	private long pop3Count;
	private long smtpCount;
	private long webreceiveCount;
	private long websendCount;
	private long savedraftCount;
	private long receivedraftCount;
	private long penetrationCount;

	@Override
	@SuppressWarnings("unchecked")
	public String execute() {
		httpCount = baseService.getTheDayCount(HttpRecord.class, null, null);
		searchCount = baseService
				.getTheDayCount(SearchRecord.class, null, null);
		throughCount = baseService.getTheDayCount(ThroughRecord.class, null,
				null);
		commerceCount = baseService.getTheDayCount(CommerceRecord.class, null,
				null);
		socialCount = baseService
				.getTheDayCount(SocialRecord.class, null, null);
		passwordCount = baseService.getTheDayCount(PasswordRecord.class, null,
				null);
		ftpCount = baseService.getTheDayCount(FtpRecord.class, null, null);
		netdiskCount = baseService.getTheDayCount(NetdiskRecord.class, null,
				null);
		controlCount = baseService.getTheDayCount(ControlRecord.class, null,
				null);
		msnCount = baseService
				.getTheDayCount(ImRecord.class, "imClassify", '1');
		qqCount = baseService.getTheDayCount(ImRecord.class, "imClassify", '2');
		fetionCount = baseService.getTheDayCount(ImRecord.class, "imClassify",
				'3');
		wechatCount = baseService.getTheDayCount(ImRecord.class, "imClassify",
				'4');
		skypeCount = baseService.getTheDayCount(ImRecord.class, "imClassify",
				'5');
		pop3Count = baseService.getTheDayCount(MailRecord.class, "classify",
				'1');
		smtpCount = baseService.getTheDayCount(SearchRecord.class, "classify",
				'2');
		webreceiveCount = baseService.getTheDayCount(SearchRecord.class,
				"classify", '3');
		websendCount = baseService.getTheDayCount(SearchRecord.class,
				"classify", '4');
		savedraftCount = baseService.getTheDayCount(SearchRecord.class,
				"classify", '5');
		receivedraftCount = baseService.getTheDayCount(SearchRecord.class,
				"classify", '6');
		penetrationCount = baseService.getTheDayCount(SearchRecord.class,
				"classify", '7');
		return SUCCESS;
	}

	public long getHttpCount() {
		return httpCount;
	}

	public void setHttpCount(long httpCount) {
		this.httpCount = httpCount;
	}


	public long getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(long searchCount) {
		this.searchCount = searchCount;
	}

	public long getThroughCount() {
		return throughCount;
	}

	public void setThroughCount(long throughCount) {
		this.throughCount = throughCount;
	}

	public long getCommerceCount() {
		return commerceCount;
	}

	public void setCommerceCount(long commerceCount) {
		this.commerceCount = commerceCount;
	}

	public long getSocialCount() {
		return socialCount;
	}

	public void setSocialCount(long socialCount) {
		this.socialCount = socialCount;
	}

	public long getPasswordCount() {
		return passwordCount;
	}

	public void setPasswordCount(long passwordCount) {
		this.passwordCount = passwordCount;
	}

	public long getFtpCount() {
		return ftpCount;
	}

	public void setFtpCount(long ftpCount) {
		this.ftpCount = ftpCount;
	}

	public long getNetdiskCount() {
		return netdiskCount;
	}

	public void setNetdiskCount(long netdiskCount) {
		this.netdiskCount = netdiskCount;
	}

	public long getControlCount() {
		return controlCount;
	}

	public void setControlCount(long controlCount) {
		this.controlCount = controlCount;
	}

	public long getMsnCount() {
		return msnCount;
	}

	public void setMsnCount(long msnCount) {
		this.msnCount = msnCount;
	}

	public long getQqCount() {
		return qqCount;
	}

	public void setQqCount(long qqCount) {
		this.qqCount = qqCount;
	}

	public long getFetionCount() {
		return fetionCount;
	}

	public void setFetionCount(long fetionCount) {
		this.fetionCount = fetionCount;
	}

	public long getWechatCount() {
		return wechatCount;
	}

	public void setWechatCount(long wechatCount) {
		this.wechatCount = wechatCount;
	}

	public long getSkypeCount() {
		return skypeCount;
	}

	public void setSkypeCount(long skypeCount) {
		this.skypeCount = skypeCount;
	}

	public long getPop3Count() {
		return pop3Count;
	}

	public void setPop3Count(long pop3Count) {
		this.pop3Count = pop3Count;
	}

	public long getSmtpCount() {
		return smtpCount;
	}

	public void setSmtpCount(long smtpCount) {
		this.smtpCount = smtpCount;
	}

	public long getWebreceiveCount() {
		return webreceiveCount;
	}

	public void setWebreceiveCount(long webreceiveCount) {
		this.webreceiveCount = webreceiveCount;
	}

	public long getWebsendCount() {
		return websendCount;
	}

	public void setWebsendCount(long websendCount) {
		this.websendCount = websendCount;
	}

	public long getSavedraftCount() {
		return savedraftCount;
	}

	public void setSavedraftCount(long savedraftCount) {
		this.savedraftCount = savedraftCount;
	}

	public long getReceivedraftCount() {
		return receivedraftCount;
	}

	public void setReceivedraftCount(long receivedraftCount) {
		this.receivedraftCount = receivedraftCount;
	}

	public long getPenetrationCount() {
		return penetrationCount;
	}

	public void setPenetrationCount(long penetrationCount) {
		this.penetrationCount = penetrationCount;
	}

}
