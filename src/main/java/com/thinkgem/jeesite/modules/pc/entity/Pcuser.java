/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 管理平台用户Entity
 * 
 * @author tfc
 * @version 2017-09-22
 */
public class Pcuser extends DataEntity<Pcuser> {

	private static final long serialVersionUID = 1L;
	private String username; // 用户登陆用用户名
	private String realname; // 用户显示用户名
	private String password; // 登陆密码
	private String passwordModify = "no";

	private String mobile; // 用户手机号码
	private Integer isOnshop; // 是否开通在线店铺，0 - 没有（默认），1 - 开通
	private Integer isTransline;; // 是否专线用户，0- 不是（默认），1-是
	private Integer usertype; // 类型//select/1 - 普通用户,2 - , 3 - 非物流公司, 4 -物流公司, 5 -个人(司机), 6 -车队
	private Date createTime; // 该用户添加时间
	private Date updateTime; // 该用户信息最后更新时间
	private String email; // 邮箱地址
	private String remark; // 备注
	private String createId; // 创建者
	private String roleId; // 角色
	private String titleImgUuid; // 头像
	private String status;; // 用户账户当前状态。0 - 删除，1 - 激活， 2 - 待激活，3 - 禁用
	private String departId; // depart_id
	private String thirdId; // third_id
	private Date endTime; // end_time
	private Integer isLocked; // 账户是否被锁定，0 - 未有，1 - 锁定

	private Date beginDate; // 开始时间
	private Date endDate; // 结束时间

	private Pccompany pccompany;
	private Shop shop;

	public Pcuser() {
		super();
	}

	public Pcuser(String id) {
		super(id);
	}

	@Length(min = 0, max = 15, message = "用户登陆用用户名长度必须介于 0 和 15 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Length(min = 0, max = 15, message = "用户显示用户名长度必须介于 0 和 15 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Length(min = 0, max = 32, message = "登陆密码长度必须介于 0 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min = 0, max = 30, message = "用户手机号码长度必须介于 0 和 30 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getIsOnshop() {
		return isOnshop;
	}

	public void setIsOnshop(Integer isOnshop) {
		this.isOnshop = isOnshop;
	}

	public Integer getIsTransline() {
		return isTransline;
	}

	public void setIsTransline(Integer isTransline) {
		this.isTransline = isTransline;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
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

	@Length(min = 0, max = 64, message = "邮箱地址长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 0, max = 1000, message = "备注长度必须介于 0 和 1000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Length(min = 0, max = 11, message = "创建者长度必须介于 0 和 11 之间")
	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Length(min = 0, max = 11, message = "角色长度必须介于 0 和 11 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Length(min = 0, max = 9, message = "头像长度必须介于 0 和 9 之间")
	public String getTitleImgUuid() {
		return titleImgUuid;
	}

	public void setTitleImgUuid(String titleImgUuid) {
		this.titleImgUuid = titleImgUuid;
	}

	@Length(min = 0, max = 11, message = "depart_id长度必须介于 0 和 11 之间")
	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	@Length(min = 0, max = 200, message = "third_id长度必须介于 0 和 200 之间")
	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Pccompany getPccompany() {
		return pccompany;
	}

	public void setPccompany(Pccompany pccompany) {
		this.pccompany = pccompany;
	}

	public String getPasswordModify() {
		return passwordModify;
	}

	public void setPasswordModify(String passwordModify) {
		this.passwordModify = passwordModify;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}