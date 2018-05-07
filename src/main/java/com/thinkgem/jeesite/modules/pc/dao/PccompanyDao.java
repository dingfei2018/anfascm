/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pc.entity.Pccompany;

/**
 * 平台物流公司DAO接口
 * @author tfc
 * @version 2017-09-26
 */
@MyBatisDao
public interface PccompanyDao extends CrudDao<Pccompany> {
	
}