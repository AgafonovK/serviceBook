package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRoleRepository extends JpaRepository <AppRole, Long> {

    @Query("select ar.roleName from AppRole ar where ar.appUser.userId = :userId ")
    List<String>getRoleNames(@Param("userId") Long userId);
}
