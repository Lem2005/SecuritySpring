package com.example.Security.SecuritySpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/Home").permitAll()  
                        .anyRequest().authenticated()  
                )
                .formLogin((form) -> form
                        .loginPage("/Login")  
                        .permitAll()  
                )
                .logout((logout) -> logout.permitAll());  
                
        return http.build();
    } 
    
    @Bean
    public UserDetailsService userDetailsService() {
        
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345") 
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(user);  
    }
    
    
}
