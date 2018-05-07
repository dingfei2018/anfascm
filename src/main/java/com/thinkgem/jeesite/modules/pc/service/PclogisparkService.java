/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pc.entity.Pclogispark;
import com.thinkgem.jeesite.modules.pc.dao.PclogisparkDao;

/**
 * 物流园Service
 * @author tfc
 * @version 2017-10-16
 */
@Service
@Transactional(readOnly = true)
public class PclogisparkService extends CrudService<PclogisparkDao, Pclogispark> {

	public Pclogispark get(String id) {
		return super.get(id);
	}
	
	public List<Pclogispark> findList(Pclogispark pclogispark) {
		return super.findList(pclogispark);
	}
	
	public Page<Pclogispark> findPage(Page<Pclogispark> page, Pclogispark pclogispark) {
		return super.findPage(page, pclogispark);
	}
	
	@Transactional(readOnly = false)
	public void save(Pclogispark pclogispark) {
		super.save(pclogispark);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pclogispark pclogispark) {
		super.delete(pclogispark);
	}
	
}