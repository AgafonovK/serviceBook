package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.AppUserRepository;
import com.xvr.serviceBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Override
    public AppUser addUser(AppUser appUser) {
        AppUser savedAppUser = appUserRepository.saveAndFlush(appUser);
        return savedAppUser;
    }





}
