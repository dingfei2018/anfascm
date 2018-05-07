/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pc.entity.Shop;
import com.thinkgem.jeesite.modules.pc.dao.ShopDao;

/**
 * 店铺管理Service
 * @author tfc
 * @version 2017-10-31
 */
@Service
@Transactional(readOnly = true)
public class ShopService extends CrudService<ShopDao, Shop> {

	public Shop get(String id) {
		return super.get(id);
	}
	
	public List<Shop> findList(Shop shop) {
		return super.findList(shop);
	}
	
	public Page<Shop> findPage(Page<Shop> page, Shop shop) {
		return super.findPage(page, shop);
	}
	
	@Transactional(readOnly = false)
	public void save(Shop shop) {
		super.save(shop);
	}
	
	@Transactional(readOnly = false)
	public void delete(Shop shop) {
		super.delete(shop);
	}
	
}