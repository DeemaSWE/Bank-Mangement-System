package com.example.bankmanagementsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    private String email;

    @Pattern(regexp = "^(ADMIN|EMPLOYEE)$", message = "Role must be EMPLOYEE or ADMIN")
    private String role;

    @NotEmpty(message = "Position cannot be empty")
    private String position;

    @NotNull(message = "Salary cannot be null")
    @PositiveOrZero(message = "Salary must be positive or zero")
    private Double salary;
}
