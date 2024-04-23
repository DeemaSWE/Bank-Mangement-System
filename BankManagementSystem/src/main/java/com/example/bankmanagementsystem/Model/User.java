package com.example.bankmanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(10) not null check (length(username) >= 4 and length(username) <= 10)", unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(columnDefinition = "varchar(60) not null")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null check (length(name) >= 2 and length(name) <= 20)")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    @Column(columnDefinition = "varchar(100)", unique = true)
    private String email;

    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$", message = "Role must be either CUSTOMER or EMPLOYEE or ADMIN")
    @Column(columnDefinition = "varchar(10) check (role in ('CUSTOMER','EMPLOYEE','ADMIN'))")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
