package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRoleRepository extends JpaRepository <AppRole, Long> {

    /** work query
     * select ar.roleName from AppRole ar inner join UserRole ur on ar = ur.appRole inner join AppUser au on ur.user = au where au.userId = :userId
     */
    @Query("select ur.appRole.roleName from UserRole ur where ur.appUser.userId = :userId")
    List<String> getRoleNames(@Param("userId") Long userId);
}
