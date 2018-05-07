/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pc.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;


/**
 * 文章Entity
 * 
 * @author ThinkGem
 * @version 2013-05-15
 */
public class LibImage extends DataEntity<LibImage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String gid;
	private String image;
	private String tag;
	private String host;
	private String imageUri;

	public LibImage() {
		super();
	}

	public LibImage(String uuid) {
		this();
		this.uuid = uuid;
	}

	@Override
	public String getId() {
		return uuid;
	}

	@Override
	public void setId(String id) {
		this.uuid = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getImageUri() {
		return "http://"+getHost()+getImage();
	}




}
