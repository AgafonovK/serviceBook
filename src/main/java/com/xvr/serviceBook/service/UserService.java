package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends UserDetailsService {

    AppUser addUser(AppUser appUser);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
