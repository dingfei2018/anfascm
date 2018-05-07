/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文章Entity
 * 
 * @author ThinkGem
 * @version 2013-05-15
 */
public class AddressBook extends DataEntity<AddressBook> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String regionCode;
	private String townCode;
	private String fullAddress;
	private String locationXY;
	private Date createTime;
	private String userId;
	private String tailAddress;

	public AddressBook() {
		super();
	}

	public AddressBook(String id) {
		this();
		this.id = id;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getTailAddress() {
		return tailAddress;
	}

	public void setTailAddress(String tailAddress) {
		this.tailAddress = tailAddress;
	}

	public String getLocationXY() {
		return locationXY;
	}

	public void setLocationXY(String locationXY) {
		this.locationXY = locationXY;
	}

}
