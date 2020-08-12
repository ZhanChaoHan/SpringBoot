package com.jachs.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    UserDetails loadUserByUsername(String username,String password);
}
