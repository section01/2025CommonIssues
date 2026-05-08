package com.example.kintai.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AttendanceSearchRequest {

	private Boolean selfOnly;
	private String empId;
	private String teamId;
	private String name;
	private LocalDate dateFrom;
	private LocalDate dateTo;

}