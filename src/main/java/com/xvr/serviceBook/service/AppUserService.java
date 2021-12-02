package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import com.xvr.serviceBook.service.servicedto.WorkerServiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public interface AppUserService extends UserDetailsService {

    void saveUser(AppUserServiceDto appUserServiceDto);
    List<AppUser> findAllAppUsers();
    Page<AppUser> findAllAppUsersPaginated(Pageable pageable);

    Optional<AppUser> findAppUserById(Long id);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;


}
