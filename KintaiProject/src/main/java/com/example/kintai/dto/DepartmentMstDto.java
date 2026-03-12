package com.example.kintai.dto;

public class DepartmentMstDto {
	// 部署ID
	private String departmentId;
	// 部署名
	private String departName;
	
	public DepartmentMstDto(String departmentId, 	String departName) {
		this.departmentId = departmentId;
		this.departName = departName;		
	}
	
	// getter / setter
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
	
}
