package com.vamsi.controller;

import com.vamsi.model.Employee;
import com.vamsi.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/health")
    public String health() {
        return "Application is Healthy";
    }

    @GetMapping("/version")
    public String version() {
        return "Version 1.0";
    }
}