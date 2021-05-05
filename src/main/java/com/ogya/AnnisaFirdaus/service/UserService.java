package com.ogya.AnnisaFirdaus.service;

import com.ogya.AnnisaFirdaus.domain.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
}