package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface AppUserService extends UserDetailsService {

    void saveUser(AppUserServiceDto appUserServiceDto);

    List<AppUser> findAllAppUsers();
    Page<AppUser> findAllAppUsersPaginated(Pageable pageable);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


}
