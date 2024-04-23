package com.example.bankmanagementsystem.SecurityConfig;

import com.example.bankmanagementsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/employee/register", "/api/v1/customer/register").permitAll()
                .requestMatchers("/api/v1/auth/get-all", "/api/v1/employee/get-all",
                        "/api/v1/customer/get-all", "/api/v1/account/get-all", "/api/v1/account/block", "/api/v1/account/activate").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers( "/api/v1/employee/delete/**").hasAnyAuthority("EMPLOYEE", "ADMIN")
                .requestMatchers("/api/v1/customer/update", "/api/v1/account/create", "/api/v1/account/update/**",
                        "/api/v1/account/view/**", "/api/v1/account/deposit/**", "/api/v1/account/withdraw/**", "/api/v1/account/transfer/**").hasAuthority("CUSTOMER")
                .requestMatchers( "/api/v1/customer/delete/**", "/api/v1/account/delete/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
