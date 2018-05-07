/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 店铺管理Entity
 * 
 * @author tfc
 * @version 2017-10-31
 */
public class Shop extends DataEntity<Shop> {

	private static final long serialVersionUID = 1L;
	private String shopName; // 店铺显示名称
	private String shopSubdomain; // 用于访问店铺的子域名
	private String shopDesc; // 店铺描述
	private String shopDescShort; // 公司简介
	private String figureImgGid; // 公司网站形象照片-图片库图片组id
	private String scrollImgGid; // 首页宣传图片-图片库图片组id
	private Date createTime; // 店铺首次开通时间
	private Date updateTime; // 店铺信息最后更新时间
	private String theme; // 店铺主题
	private String cultureDesc; // 公司品牌简述
	private String cultureImgGid; // 品牌相关图片
	private String cultureJzg; // 公司价值观
	private String cultureSm; // 公司使命
	private String showYyzz; // 是否显示公司营业执照，1-显示，0-不显示(默认)
	private String showSfz; // 是否显示公司法人/责任人身份证，1-显示，0-不显示(默认)
	private String showMobile; // 是否显示公司法人/负责人手机号码，1-显示，0-不显示(默认)
	private String showNetworkMobile; // 是否显示公司网点负责人手机号码，1-显示，0-不显示(默认)

	public Shop() {
		super();
	}

	public Shop(String id) {
		super(id);
	}

	@Length(min = 0, max = 125, message = "店铺显示名称长度必须介于 0 和 125 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Length(min = 0, max = 15, message = "用于访问店铺的子域名长度必须介于 0 和 15 之间")
	public String getShopSubdomain() {
		return shopSubdomain;
	}

	public void setShopSubdomain(String shopSubdomain) {
		this.shopSubdomain = shopSubdomain;
	}

	public String getShopDesc() {
		return shopDesc;
	}

	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}

	@Length(min = 0, max = 450, message = "公司简介长度必须介于 0 和 450 之间")
	public String getShopDescShort() {
		return shopDescShort;
	}

	public void setShopDescShort(String shopDescShort) {
		this.shopDescShort = shopDescShort;
	}

	@Length(min = 0, max = 9, message = "公司网站形象照片-图片库图片组id长度必须介于 0 和 9 之间")
	public String getFigureImgGid() {
		return figureImgGid;
	}

	public void setFigureImgGid(String figureImgGid) {
		this.figureImgGid = figureImgGid;
	}

	@Length(min = 0, max = 9, message = "首页宣传图片-图片库图片组id长度必须介于 0 和 9 之间")
	public String getScrollImgGid() {
		return scrollImgGid;
	}

	public void setScrollImgGid(String scrollImgGid) {
		this.scrollImgGid = scrollImgGid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Length(min = 0, max = 64, message = "店铺主题长度必须介于 0 和 64 之间")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCultureDesc() {
		return cultureDesc;
	}

	public void setCultureDesc(String cultureDesc) {
		this.cultureDesc = cultureDesc;
	}

	@Length(min = 0, max = 9, message = "品牌相关图片长度必须介于 0 和 9 之间")
	public String getCultureImgGid() {
		return cultureImgGid;
	}

	public void setCultureImgGid(String cultureImgGid) {
		this.cultureImgGid = cultureImgGid;
	}

	public String getCultureJzg() {
		return cultureJzg;
	}

	public void setCultureJzg(String cultureJzg) {
		this.cultureJzg = cultureJzg;
	}

	public String getCultureSm() {
		return cultureSm;
	}

	public void setCultureSm(String cultureSm) {
		this.cultureSm = cultureSm;
	}

	@Length(min = 1, max = 1, message = "是否显示公司营业执照，1-显示，0-不显示(默认)长度必须介于 1 和 1 之间")
	public String getShowYyzz() {
		return showYyzz;
	}

	public void setShowYyzz(String showYyzz) {
		this.showYyzz = showYyzz;
	}

	@Length(min = 1, max = 1, message = "是否显示公司法人/责任人身份证，1-显示，0-不显示(默认)长度必须介于 1 和 1 之间")
	public String getShowSfz() {
		return showSfz;
	}

	public void setShowSfz(String showSfz) {
		this.showSfz = showSfz;
	}

	@Length(min = 1, max = 1, message = "是否显示公司法人/负责人手机号码，1-显示，0-不显示(默认)长度必须介于 1 和 1 之间")
	public String getShowMobile() {
		return showMobile;
	}

	public void setShowMobile(String showMobile) {
		this.showMobile = showMobile;
	}

	@Length(min = 1, max = 1, message = "是否显示公司网点负责人手机号码，1-显示，0-不显示(默认)长度必须介于 1 和 1 之间")
	public String getShowNetworkMobile() {
		return showNetworkMobile;
	}

	public void setShowNetworkMobile(String showNetworkMobile) {
		this.showNetworkMobile = showNetworkMobile;
	}

}