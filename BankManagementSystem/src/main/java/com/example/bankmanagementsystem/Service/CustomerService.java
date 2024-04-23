package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public void register(CustomerDTO customerDTO){

        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        User user = new User(null, customerDTO.getUsername(), hashPassword, customerDTO.getName(), customerDTO.getEmail(), "CUSTOMER", null, null);
        authRepository.save(user);

        Customer customer = new Customer(null, customerDTO.getPhoneNumber(), user, null);
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void updateCustomer(Integer userId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepository.findCustomerById(userId);
        User user = customer.getUser();

        user.setUsername(updatedCustomer.getUsername());
        user.setName(updatedCustomer.getName());
        user.setEmail(updatedCustomer.getEmail());
        authRepository.save(user);

        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer userId, Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        User user = authRepository.findUserById(userId);

        if(customer == null)
            throw new ApiException("Customer not found");

        if(user.getRole().equals("CUSTOMER") && customer.getId() != userId)
            throw new ApiException("You are not authorized to delete this customer");

        authRepository.delete(user);
    }
}
