package com.example.favoriteproducts.infrastructure.service;

import com.example.favoriteproducts.infrastructure.security.model.UserDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserDetailService {
    UserDetail loadUserByUsername(String username) throws UsernameNotFoundException;
}