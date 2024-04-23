package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public void register(EmployeeDTO employeeDTO){

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User(null, employeeDTO.getUsername(), hashPassword, employeeDTO.getName(), employeeDTO.getEmail(), "EMPLOYEE", null, null);
        authRepository.save(user);

        Employee employee = new Employee(null, employeeDTO.getPosition(), employeeDTO.getSalary(), user);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void updateEmployee(Integer userId, EmployeeDTO updatedEmployee) {
        Employee employee = employeeRepository.findEmployeeById(userId);
        User user = employee.getUser();

        user.setUsername(updatedEmployee.getUsername());
        user.setName(updatedEmployee.getName());
        user.setEmail(updatedEmployee.getEmail());
        authRepository.save(user);

        employee.setPosition(updatedEmployee.getPosition());
        employee.setSalary(updatedEmployee.getSalary());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer userId, Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        User user = authRepository.findUserById(userId);

        if(employee == null)
            throw new ApiException("Employee not found");

        if(user.getRole().equals("EMPLOYEE") && employee.getId() != userId)
            throw new ApiException("You are not authorized to delete this employee");

        authRepository.delete(user);
    }
}
