package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AppRoleRepository extends JpaRepository <AppRole, Long> {

    @Query("select ar.roleName from AppRole ar join ar.appUsers au where au.userId = :userId")
    List<String> getRoleNames(@Param("userId") Long userId);
}
