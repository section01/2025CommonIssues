package com.example.kintai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kintai.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository repository;

	public List<Map<String,Object>> search(
			String empId,
			String teamId,
			LocalDate dateFrom,
			LocalDate dateTo){

		List<Object[]> list =
				repository.search(empId,teamId,dateFrom,dateTo);

		List<Map<String,Object>> result = new ArrayList<>();

		for(Object[] row : list){

			Map<String,Object> map = new HashMap<>();

			map.put("name",row[0]);
			map.put("teamId",row[1]);
			map.put("date",row[2]);
			map.put("reason",row[3]);
			map.put("delayTime",row[4]);
			map.put("detail",row[5]);

			result.add(map);
		}

		return result;
	}
}