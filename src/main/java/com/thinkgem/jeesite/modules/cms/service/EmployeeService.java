package com.thinkgem.jeesite.modules.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.cms.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private Employee employee;

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee e) {
		this.employee = e;
	}
}
