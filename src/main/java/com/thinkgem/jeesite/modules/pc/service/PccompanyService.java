/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;


import com.thinkgem.jeesite.modules.pc.dao.PccompanyDao;

/**
 * 平台物流公司Service
 * @author tfc
 * @version 2017-09-26
 */
@Service
@Transactional(readOnly = true)
public class PccompanyService extends CrudService<PccompanyDao, Pccompany> {

	public Pccompany get(String id) {
		return super.get(id);
	}
	
	public List<Pccompany> findList(Pccompany pccompany) {
		return super.findList(pccompany);
	}
	
	public Page<Pccompany> findPage(Page<Pccompany> page, Pccompany pccompany) {
		return super.findPage(page, pccompany);
	}
	
	@Transactional(readOnly = false)
	public void save(Pccompany pccompany) {
		super.save(pccompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(Pccompany pccompany) {
		super.delete(pccompany);
	}
	
	@Transactional(readOnly = false)
	public void updateVerify(Pccompany pccompany) {
		if(StringUtils.isNotBlank(pccompany.getRegToYear())
				|| StringUtils.isNotBlank(pccompany.getIsIcap())
				|| StringUtils.isNotBlank(pccompany.getIsIcBlack())) {
			if(pccompany.getIsIcap() ==null) {
				pccompany.setIsIcap("0");
			}
			if(pccompany.getIsIcBlack() ==null) {
				pccompany.setIsIcBlack("0");
			}
			super.save(pccompany);
		}		
	}
}