/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.dao;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;

/**
 * 管理平台用户DAO接口
 * @author tfc
 * @version 2017-09-22
 */
@MyBatisDao
public interface PcuserDao extends CrudDao<Pcuser> {
}