package com.example.Bai6.service;

import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import com.example.Bai6.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

 private final UserRepository repo;

 public UserService(UserRepository repo){
     this.repo = repo;
 }

 @Override
 public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {

     var user = repo.findByUsername(username)
             .orElseThrow(() ->
             new UsernameNotFoundException("User not found"));

     return org.springframework.security.core.userdetails.User
             .withUsername(user.getUsername())
             .password(user.getPassword())
             .roles(user.getRole())
             .build();
 }
}