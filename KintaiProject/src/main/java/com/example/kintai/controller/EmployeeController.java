package com.example.kintai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kintai.entity.Employee;
import com.example.kintai.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String empId) {

        Optional<Employee> employee = employeeRepository.findById(empId);

        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
