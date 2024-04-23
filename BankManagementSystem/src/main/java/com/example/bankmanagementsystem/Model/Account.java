package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

    @PositiveOrZero(message = "Balance must be positive or zero")
    @Column(columnDefinition = "double not null check (balance >= 0)")
    private Double balance;

    @AssertFalse
    @Column(columnDefinition = "boolean default false")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
