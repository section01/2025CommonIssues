package com.example.kintai.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AttendanceSearchRequest {

    private String empId;
    private String teamId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}