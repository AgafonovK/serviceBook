package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.service.AppRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;

    public AppRoleServiceImpl(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public Page<AppRole> findAll(Pageable pageable) {
        return appRoleRepository.findAll(pageable);
    }
}
