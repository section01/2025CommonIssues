package com.example.kintai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DepartmentMst {
	// 部署ID
	@Id
	private String departmentId;
	// 部署名
	private String departmentName;
	
	// getter / setter
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
