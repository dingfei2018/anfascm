/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.dao.PcuserDao;

/**
 * 管理平台用户Service
 * @author tfc
 * @version 2017-09-22
 */
@Service
@Transactional(readOnly = true)
public class PcuserService extends CrudService<PcuserDao, Pcuser> {

	public Pcuser get(String id) {
		return super.get(id);
	}
	
	public List<Pcuser> findList(Pcuser pcuser) {
		return super.findList(pcuser);
	}
	
	public Page<Pcuser> findPage(Page<Pcuser> page, Pcuser pcuser) {
		return super.findPage(page, pcuser);
	}
	
	@Transactional(readOnly = false)
	public void save(Pcuser pcuser) {
		super.save(pcuser);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pcuser pcuser) {
		super.delete(pcuser);
	}
}