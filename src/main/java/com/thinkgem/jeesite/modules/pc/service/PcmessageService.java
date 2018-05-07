/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pc.entity.Pcmessage;
import com.thinkgem.jeesite.modules.pc.dao.PcmessageDao;

/**
 * 站内信Service
 * @author tfc
 * @version 2017-10-12
 */
@Service
@Transactional(readOnly = true)
public class PcmessageService extends CrudService<PcmessageDao, Pcmessage> {

	public Pcmessage get(String id) {
		return super.get(id);
	}
	
	public List<Pcmessage> findList(Pcmessage pcmessage) {
		return super.findList(pcmessage);
	}
	
	public Page<Pcmessage> findPage(Page<Pcmessage> page, Pcmessage pcmessage) {
		return super.findPage(page, pcmessage);
	}
	
	@Transactional(readOnly = false)
	public void save(Pcmessage pcmessage) {
		super.save(pcmessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pcmessage pcmessage) {
		super.delete(pcmessage);
	}
	
}