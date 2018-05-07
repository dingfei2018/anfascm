/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.help.core.utils.UUIDUtil;
import com.thinkgem.jeesite.common.annotation.DynamicSourcedb;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.pc.dao.AddressBookDao;
import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.Pcmessage;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.entity.VerifyData;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 文章Service
 * 
 * @author ThinkGem
 * @version 2013-05-15
 */
@Service
@Transactional(readOnly = true)
public class AddressBookService extends CrudService<AddressBookDao, AddressBook> {

	@Transactional(readOnly = false)
	public Page<AddressBook> findPage(Page<AddressBook> page, AddressBook book) {
		return super.findPage(page, book);
	}

	@Transactional(readOnly = false)
	public void save(AddressBook book) {
		if (StringUtils.isEmpty(book.getRegionCode()) && StringUtils.isEmpty(book.getTailAddress())) {
			book.setIsNewRecord(false);
			return;
		}
		if (StringUtils.isEmpty(book.getId())) {
			book.setId(UUIDUtil.UUID());
			book.setIsNewRecord(true);
			book.setCreateTime(new Date());
			book.setUserId("0");
		} else {
			book.setIsNewRecord(false);
		}
		super.save(book);
	}
}
