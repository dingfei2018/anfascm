/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pc.entity.LibImage;
import com.thinkgem.jeesite.modules.pc.entity.Pcmessage;

/**
 * 站内信DAO接口
 * @author tfc
 * @version 2017-10-12
 */
@MyBatisDao
public interface PcmessageDao extends CrudDao<Pcmessage> {
	public void sendMsgToUser(Pcmessage pcmessage);
}