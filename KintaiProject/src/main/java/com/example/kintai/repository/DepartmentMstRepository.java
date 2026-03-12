package com.example.kintai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kintai.entity.DepartmentMst;

public interface DepartmentMstRepository extends JpaRepository<DepartmentMst, String> {
    // 部署IDで検索
	Optional<DepartmentMst> findByDepartmentId(String keyword);

}
