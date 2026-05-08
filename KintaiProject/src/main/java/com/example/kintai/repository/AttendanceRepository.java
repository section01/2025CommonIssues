package com.example.kintai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.kintai.entity.AttendanceNotice;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceNotice,String>{

/*	@Query(value = """
		SELECT
			a.RECORD_ID,
			e.EMP_LNAME,
			t.TEAM_ID,
			a.NOTICES_DATE,

			CASE a.DELAY_REASON
				WHEN '1' THEN '電車遅延'
				WHEN '2' THEN '寝坊'
				WHEN '3' THEN '体調不良'
				WHEN '9' THEN 'その他'
			END AS REASON,

			a.DELAY_TIME,
			a.MAIN_TEXT

		FROM
			ATTENDANCE_NOTICES a
		JOIN
			EMPLOYEE_MST e
		ON
			a.EMP_ID = e.EMP_ID

		JOIN
			TEAM_MST t
		ON
			a.TEAM_ID = t.TEAM_ID

		WHERE
			(:empId IS NULL OR a.EMP_ID = :empId)
		AND
			(:teamId IS NULL OR a.TEAM_ID = :teamId)
		AND 
			a.notices_date >= COALESCE(:dateFrom, a.notices_date)
		AND
			a.notices_date <= COALESCE(:dateTo, a.notices_date)
	""", nativeQuery = true)
	List<Object[]> search(
			String empId,
			String teamId,
			LocalDate dateFrom,
			LocalDate dateTo
	);
*/
	@Query(value = """
			SELECT
				a.RECORD_ID,
				e.EMP_LNAME AS name,
				t.TEAM_NAME AS teamName,
				a.NOTICES_DATE,

				CASE a.DELAY_REASON
					WHEN '1' THEN '電車遅延'
					WHEN '2' THEN '寝坊'
					WHEN '3' THEN '体調不良'
					WHEN '9' THEN 'その他'
				END AS reason,
				
					a.DELAY_TIME,
					a.MAIN_TEXT

			FROM
				attendance_notices a
			LEFT JOIN
				employee_mst e
			ON
				a.emp_id = e.emp_id

			LEFT JOIN
				team_mst t 
			ON
				a.team_id = t.team_id

			WHERE
				a.notices_date >= COALESCE(:dateFrom, a.notices_date)
		  	AND
		  		a.notices_date <= COALESCE(:dateTo, a.notices_date)
		""", nativeQuery = true)
		List<Object[]> search(
				LocalDate dateFrom,
				LocalDate dateTo
		);
}