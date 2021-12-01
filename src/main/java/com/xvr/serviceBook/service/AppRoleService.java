package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.AppRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleService {

    Page<AppRole> findAll(Pageable pageable);
}
