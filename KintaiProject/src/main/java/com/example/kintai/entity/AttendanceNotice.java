// 最低限の項目のみ実装
package com.example.kintai.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ATTENDANCE_NOTICES")
@Data
public class AttendanceNotice {

	@Id
	@Column(name = "RECORD_ID")
	private String recordId;

	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "TEAM_ID")
	private String teamId;

	@Column(name = "NOTICES_DATE")
	private LocalDate noticesDate;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "DELAY_REASON")
	private String delayReason;

	@Column(name = "DELAY_TIME")
	private String delayTime;

	@Column(name = "MAIN_TEXT")
	private String mainText;

}