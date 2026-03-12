// 最低限の項目のみ実装
package com.example.kintai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee_mst")
@Data
public class Employee {

    @Id
    @Column(name = "emp_id", length = 10)
    private String empId;

    @Column(name = "department_id", length = 1)
    private String departmentId;

    @Column(name = "team_id", length = 5)
    private String teamId;

    @Column(name = "role", length = 1)
    private Integer role;

    @Column(name = "emp_lname", length = 10)
    private String empLname;

    @Column(name = "emp_fname", length = 10)
    private String empFname;

    @Column(name = "mail_address", length = 100)
    private String mailAddress;

    // getter / setter
}
