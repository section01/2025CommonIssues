package com.example.kintai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.kintai.entity.AttendanceNotice;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceNotice,String>{

	@Query(value = """
		SELECT
			e.NAME,
			e.TEAM_ID,
			a.NOTICES_DATE,
			a.DELAY_REASON,
			a.DELAY_TIME,
			a.MAIN_TEXT
		FROM
			ATTENDANCE_NOTICES a
		JOIN
			EMPLOYEE e
		ON
			a.EMP_ID = e.EMP_ID
		WHERE
			(:empId IS NULL OR a.EMP_ID = :empId)
		AND
			(:teamId IS NULL OR a.TEAM_ID = :teamId)
		AND
			(:dateFrom IS NULL OR a.NOTICES_DATE >= :dateFrom)
		AND
			(:dateTo IS NULL OR a.NOTICES_DATE <= :dateTo)
	""", nativeQuery = true)
	List<Object[]> search(
			String empId,
			String teamId,
			LocalDate dateFrom,
			LocalDate dateTo
	);

}