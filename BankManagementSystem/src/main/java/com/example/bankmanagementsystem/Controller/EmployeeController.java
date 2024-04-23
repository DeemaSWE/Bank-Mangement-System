package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeDTO employee){
        employeeService.register(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }

    // Admin
    @GetMapping("/get-all")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    // Employee
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employee){
        employeeService.updateEmployee(user.getId(), employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }

    // Employee and Admin
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User user, @PathVariable Integer employeeId){
        employeeService.deleteEmployee(user.getId(), employeeId);
        return ResponseEntity.status(200).body(new ApiResponse("Employee deleted successfully"));
    }
}
