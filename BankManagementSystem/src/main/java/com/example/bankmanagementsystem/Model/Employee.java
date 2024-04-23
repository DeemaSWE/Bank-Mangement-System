package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Employee {

    @Id
    private Integer id;

    @NotEmpty(message = "Position cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String position;

    @PositiveOrZero(message = "Salary must be positive or zero")
    @Column(columnDefinition = "double not null check (salary >= 0)")
    private Double salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
}
