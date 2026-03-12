package com.example.kintai.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kintai.dto.AttendanceSearchRequest;
import com.example.kintai.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:8081")
public class AttendanceController {

	@Autowired
	AttendanceService service;

	@PostMapping("/search")
	public List<Map<String,Object>> search(
			@RequestBody AttendanceSearchRequest req){

		return service.search(
				req.getEmpId(),
				req.getTeamId(),
				req.getDateFrom(),
				req.getDateTo()
		);
	}
}