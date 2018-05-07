/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 站内信Entity
 * 
 * @author tfc
 * @version 2017-10-12
 */
public class Pcmessage extends DataEntity<Pcmessage> {

	private static final long serialVersionUID = 1L;
	private String label = "系统提示"; // 消息标题
	private String content; // 消息正文内容
	private Date created; // 创建日期
	private String sender = "0"; // 发送者-用户id，id为0即为系统发送
	private String toer; // 发送者-用户id，id为0即为系统发送
	private Integer type = 1; // 消息分类，1 - 系统提示，2 - 交易通知，3 - 货源提醒

	public Pcmessage() {
		super();
	}

	public Pcmessage(String id) {
		super(id);
	}

	@Length(min = 1, max = 225, message = "消息标题长度必须介于 1 和 225 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Length(min = 1, max = 1245, message = "消息正文内容长度必须介于 1 和 1245 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "创建日期不能为空")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Length(min = 1, max = 11, message = "发送者-用户id，id为0即为系统发送长度必须介于 1 和 11 之间")
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@NotNull(message = "消息分类，1 - 系统提示，2 - 交易通知，3 - 货源提醒不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getToer() {
		return toer;
	}

	public void setToer(String toer) {
		this.toer = toer;
	}

}