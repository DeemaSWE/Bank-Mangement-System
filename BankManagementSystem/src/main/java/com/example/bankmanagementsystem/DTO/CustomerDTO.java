package com.example.bankmanagementsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

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

    @Pattern(regexp = "^(CUSTOMER)$", message = "Role must be either CUSTOMER")
    private String role;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be followed by 8 digits")
    private String phoneNumber;
}
