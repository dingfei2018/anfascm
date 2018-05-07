/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import java.util.Date;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文章Entity
 * 
 * @author ThinkGem
 * @version 2013-05-15
 */
public class VerifyData extends DataEntity<VerifyData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String statusDesc;
	private String reason;
	private String scmCheckerId;
	private Integer flowFrom;
	private Integer flowId;
	private Date createTime;
	private Date vertifyTime;

	private Date beginDate; // 开始时间
	private Date endDate; // 结束时间

	private Pcuser pcuser;
	private User chkUser;

	public VerifyData() {
		super();
	}

	public VerifyData(String id) {
		this();
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		switch (this.status) {
		case 1:
			setStatusDesc("审核中");
			break;
		case 2:
			setStatusDesc("已审核");
			break;
		case 3:
			setStatusDesc("未通过");
			break;
		default:
			break;

		}
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getScmCheckerId() {
		return scmCheckerId;
	}

	public void setScmCheckerId(String scmCheckerId) {
		this.scmCheckerId = scmCheckerId;
	}

	public Integer getFlowFrom() {
		return flowFrom;
	}

	public void setFlowFrom(Integer flowFrom) {
		this.flowFrom = flowFrom;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVertifyTime() {
		return vertifyTime;
	}

	public void setVertifyTime(Date vertifyTime) {
		this.vertifyTime = vertifyTime;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public User getChkUser() {
		return chkUser;
	}

	public void setChkUser(User chkUser) {
		this.chkUser = chkUser;
	}

	public Date getBeginDate() {
		/*
		 * Date beginDateF = this.beginDate; if (beginDateF != null){ beginDateF =
		 * DateUtils.parseDate(DateUtils.formatDate(this.beginDate, "yyyy-MM-dd")); }
		 */
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

	public Pcuser getPcuser() {
		return pcuser;
	}

	public void setPcuser(Pcuser pcuser) {
		this.pcuser = pcuser;
	}

}
