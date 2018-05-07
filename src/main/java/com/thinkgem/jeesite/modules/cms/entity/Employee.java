package com.thinkgem.jeesite.modules.cms.entity;

import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.annotation.DynamicSourcedb;

@Component
public class Employee {

	private String name;
	
	public String getName() {
		return name;
	}

	
	@DynamicSourcedb("anfapc")
	public void setName(String nm) {
		this.name=nm;
	}
	
	public void throwException(){
		throw new RuntimeException("Dummy Exception");
	}	
}
