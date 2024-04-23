package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customer){
        customerService.register(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    // Admin
    @GetMapping("/get-all")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    // Customer
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customer){
        customerService.updateCustomer(user.getId(), customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    // Customer and Admin
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customerId){
        customerService.deleteCustomer(user.getId(), customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }
}
