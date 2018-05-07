package com.thinkgem.jeesite.modules.pc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;
import com.thinkgem.jeesite.modules.pc.service.PcuserService;

/**
 * User: songlai Date: 13-8-22 Time: 上午10:23
 */
public class PcuserUtils {
	private static PcuserService pcuserService = SpringContextHolder.getBean(PcuserService.class);
	private static final String PC_USER_CACHE = "pcuserCache";

	public static Map<String, String> _userTypeMap;
	static {
		_userTypeMap = new HashMap<String, String>();
		_userTypeMap.put("1", "普通用户");
		_userTypeMap.put("2", "其他");
		_userTypeMap.put("3", "非物流公司");
		_userTypeMap.put("4", "物流公司");
		_userTypeMap.put("5", "个人(司机)");
		_userTypeMap.put("6", "车队");
	}

	public static Map<String, String> _userStatusMap;
	static {
		_userStatusMap = new HashMap<String, String>();
		_userStatusMap.put("0", "删除");
		_userStatusMap.put("1", "激活");
		_userStatusMap.put("2", "待激活");
		_userStatusMap.put("3", "禁用");
	}

	public static Map<String, String> getUserTypeMap() {
		return _userTypeMap;
	}

	public static String getUserTypeName(String key) {
		if (!_userTypeMap.containsKey(key)) {
			return "";
		}
		return _userTypeMap.get(key);
	}

	public static Map<String, String> getUserStatusMap() {
		return _userStatusMap;
	}

	public static String getPCUserName(String id) {
		String userName = "";
		if(id.equals("0")) {
			return "SYSTEM";
		}
		String user_cache_id = PC_USER_CACHE + id;
		
		Pcuser pcuser = (Pcuser) CacheUtils.get(user_cache_id, id);
		if (pcuser == null) {
			pcuser = pcuserService.get(id);
			if (pcuser != null) {
				CacheUtils.put(user_cache_id, id, pcuser);
				userName = pcuser.getUsername();
			}
		}

		return userName;
	}
}
