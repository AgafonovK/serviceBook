package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.service.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppRoleImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;

    @Autowired
    public AppRoleImpl(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppRole addAppRole(AppRole appRole) {
        AppRole savedAppRole = appRoleRepository.saveAndFlush(appRole);
        return savedAppRole;
    }
}
