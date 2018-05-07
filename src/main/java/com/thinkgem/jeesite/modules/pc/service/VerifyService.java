/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.pc.dao.PcmessageDao;
import com.thinkgem.jeesite.modules.pc.dao.VerifyDao;
import com.thinkgem.jeesite.modules.pc.entity.VerifyData;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.pc.entity.LibImage;
import com.thinkgem.jeesite.modules.pc.entity.Pcmessage;

/**
 * 文章Service
 * 
 * @author ThinkGem
 * @version 2013-05-15
 */
@Service
@Transactional(readOnly = true)
public class VerifyService extends CrudService<VerifyDao, VerifyData> {

	@Autowired
	private VerifyDao verifyDao;
	
	@Autowired
	private PcmessageDao pcmessageDao;
	
	@Transactional(readOnly = false)
	public Page<VerifyData> findPage(Page<VerifyData> page, VerifyData verify,Map<String, Object> paramMap) {
		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		verify.setBeginDate(beginDate);
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		Date queryEndDate = DateUtils.addDays(endDate, 1);
		verify.setEndDate(queryEndDate);
		return super.findPage(page, verify);
	}

	@Transactional(readOnly = false)
	public List<LibImage> getAllVerityImages(String userId) {
		return dao.getAllVerityImages(userId);
	}

	@Transactional(readOnly = false)
	public void save(VerifyData verify) {
		verify.setScmCheckerId(UserUtils.getUser().getId());
		verify.setVertifyTime(new Date());		
		
		Pcmessage pcmessage = new Pcmessage();
		pcmessage.setCreated(new Date());
		pcmessage.setToer(verify.getPcuser().getId());
		
		if(verify.getStatus()==2) {
			verify.setReason("");
			pcmessage.setContent("亲，您提交的资质材料已经审核通过，要是您还有什么疑问，请联系我们客服！");
		}else if (verify.getStatus()==3) {
			pcmessage.setContent(String.format("您提交的资质材料审核未通过，原因：%s,有疑问联系客服。",verify.getReason()));
		}		
		verifyDao.update(verify);		
		pcmessageDao.insert(pcmessage);
		pcmessageDao.sendMsgToUser(pcmessage);
	}
}
