package com.example.Bai6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

 @Bean
 public BCryptPasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
 }

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

 http
 .csrf(csrf -> csrf.disable())
 .authorizeHttpRequests(auth -> auth

     .requestMatchers("/","/login","/uploads/**").permitAll()

     // USER và ADMIN đều xem được
     .requestMatchers("/products","/products/").hasAnyRole("ADMIN","USER")

     // Chỉ ADMIN mới được CRUD
     .requestMatchers("/products/create").hasRole("ADMIN")
     .requestMatchers("/products/save").hasRole("ADMIN")
     .requestMatchers("/products/edit/**").hasRole("ADMIN")
     .requestMatchers("/products/delete/**").hasRole("ADMIN")

     .anyRequest().authenticated()
 )

 .formLogin(form -> form
         .loginPage("/login")
         .loginProcessingUrl("/login")
         .defaultSuccessUrl("/products",true)
         .permitAll()
 )

 .logout(logout -> logout
         .logoutSuccessUrl("/login")
 );

 return http.build();
}
}