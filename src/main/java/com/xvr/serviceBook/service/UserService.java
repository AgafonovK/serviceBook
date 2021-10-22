package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {

    AppUser addUser(AppUser appUser);

    List<AppUser> getAppUsers();

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


}
