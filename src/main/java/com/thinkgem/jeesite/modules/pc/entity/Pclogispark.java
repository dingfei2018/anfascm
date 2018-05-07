/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 物流园Entity
 * @author tfc
 * @version 2017-10-16
 */
public class Pclogispark extends DataEntity<Pclogispark> {
	
	private static final long serialVersionUID = 1L;
	private String parkName;		// 物流园名称
	private String parkAddrUuid;		// 物流园详细地址uuid
	private String fullAddress;		// 物流园详细地址
	private String parkRemark;		// 备注
	
	public Pclogispark() {
		super();
	}

	public Pclogispark(String id){
		super(id);
	}

	@Length(min=1, max=45, message="物流园名称长度必须介于 1 和 45 之间")
	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	@Length(min=1, max=22, message="物流园详细地址，索引地址薄长度必须介于 1 和 22 之间")
	public String getParkAddrUuid() {
		return parkAddrUuid;
	}

	public void setParkAddrUuid(String parkAddrUuid) {
		this.parkAddrUuid = parkAddrUuid;
	}
	
	@Length(min=0, max=45, message="备注长度必须介于 0 和 45 之间")
	public String getParkRemark() {
		return parkRemark;
	}

	public void setParkRemark(String parkRemark) {
		this.parkRemark = parkRemark;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	
}