/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台物流公司Entity
 * 
 * @author tfc
 * @version 2017-09-26
 */
public class Pccompany extends DataEntity<Pccompany> {

	private static final long serialVersionUID = 1L;
	private String corpname; // 公司名称
	private String bussinessCode; // 营业执照编码-15位
	private String chargePerson; // 公司负责人姓名
	private String chargeMobile; // 公司负责人联系电话
	private String corpTelphone; // 公司联系电话
	private String corpAddrUuid; // 公司详细地址
	private String certImgUuid; // 资质认证图片组
	private String regToYear;
	private String isIcap;
	private String isIcBlack;

	public Pccompany() {
		super();
	}

	public Pccompany(String id) {
		super(id);
	}

	@Length(min = 0, max = 125, message = "公司名称长度必须介于 0 和 125 之间")
	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	@Length(min = 0, max = 15, message = "营业执照编码-15位长度必须介于 0 和 15 之间")
	public String getBussinessCode() {
		return bussinessCode;
	}

	public void setBussinessCode(String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	@Length(min = 0, max = 15, message = "公司负责人姓名长度必须介于 0 和 15 之间")
	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	@Length(min = 0, max = 11, message = "公司负责人联系电话长度必须介于 0 和 11 之间")
	public String getChargeMobile() {
		return chargeMobile;
	}

	public void setChargeMobile(String chargeMobile) {
		this.chargeMobile = chargeMobile;
	}

	@Length(min = 0, max = 12, message = "公司联系电话长度必须介于 0 和 12 之间")
	public String getCorpTelphone() {
		return corpTelphone;
	}

	public void setCorpTelphone(String corpTelphone) {
		this.corpTelphone = corpTelphone;
	}

	@Length(min = 0, max = 50, message = "公司详细地址长度必须介于 0 和 50 之间")
	public String getCorpAddrUuid() {
		return corpAddrUuid;
	}

	public void setCorpAddrUuid(String corpAddrUuid) {
		this.corpAddrUuid = corpAddrUuid;
	}

	@Length(min = 0, max = 22, message = "资质认证图片组长度必须介于 0 和 22 之间")
	public String getCertImgUuid() {
		return certImgUuid;
	}

	public void setCertImgUuid(String certImgUuid) {
		this.certImgUuid = certImgUuid;
	}

	public String getRegToYear() {
		return regToYear;
	}

	public void setRegToYear(String regToYear) {
		this.regToYear = regToYear;
	}

	public String getIsIcap() {
		return isIcap;
	}

	public void setIsIcap(String isIcap) {
		this.isIcap = isIcap;
	}

	public String getIsIcBlack() {
		return isIcBlack;
	}

	public void setIsIcBlack(String isIcBlack) {
		this.isIcBlack = isIcBlack;
	}
}