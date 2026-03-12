package com.example.kintai.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kintai.dto.DepartmentMstDto;
import com.example.kintai.service.DepartmentMstService;

@RestController
@RequestMapping("/departmentMsts")
public class DepartmentMstController {

	private final DepartmentMstService departmentMstService;
	
    public DepartmentMstController(DepartmentMstService departmentMstService) {
        this.departmentMstService = departmentMstService;
    }
    
    // 1件だけ返す例
    @GetMapping("/{id}")
    public Optional<DepartmentMstDto> getUserById(@PathVariable String id) {
    	Optional<DepartmentMstDto> departmentMstDto = departmentMstService.getDepartmentMstById(id);
     	System.out.println(departmentMstDto.map(departmentMst -> new DepartmentMstDto(departmentMst.getDepartmentId(), departmentMst.getDepartName())));
    
        return departmentMstService.getDepartmentMstById(id);
    }
    
    // 疎通確認用エンドポイント
    @GetMapping("/test/dbcheck")
    public String dbCheck() {
        String testId = "1"; // ★ここでハードコーディング
        return departmentMstService.getDepartmentMstById(testId)
                .map(dept -> "接続成功: " + dept.getDepartName())
                .orElse("データが見つかりません");
    }

}
