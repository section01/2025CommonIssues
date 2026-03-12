package com.example.kintai.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.kintai.dto.DepartmentMstDto;
import com.example.kintai.repository.DepartmentMstRepository;

@Service
public class DepartmentMstService {
	
	private final DepartmentMstRepository departmentMstRepository;
	
    public DepartmentMstService(DepartmentMstRepository departmentMstRepository) {
        this.departmentMstRepository = departmentMstRepository;
    }
    
    // 部署IDで検索して DTO に変換
    public Optional<DepartmentMstDto> getDepartmentMstById(String keyword) {
        return departmentMstRepository.findByDepartmentId(keyword)
                .map(departmentMst -> new DepartmentMstDto(departmentMst.getDepartmentId(), departmentMst.getDepartmentName()));
    }

}
