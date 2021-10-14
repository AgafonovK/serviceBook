package com.xvr.serviceBook.repository;

import com.xvr.serviceBook.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRoleRepository extends JpaRepository <AppRole, Long> {

    /** work query
     * select ar.roleName from AppRole ar inner join UserRole ur on ar = ur.appRole inner join AppUser au on ur.user = au where au.userId = :userId
     * select ar.role_Name from App_Role ar inner join User_Role ur on ar.role_id = ur.role_id
     * inner join app_user au ON ur.user_id = au.user_id where au.user_id=2
     */
    //@Query("select ur.appRole.roleName from UserRole ur where ur.appUser.userId = :userId")
    @Query("select ar.roleName from AppRole ar inner join UserRole ur on ar.appRoleId = ur.userRoleId " +
            "inner join AppUser au ON ur.userRoleId = au.userId where au.userId= :userId")
    List<String> getRoleNames(@Param("userId") Long userId);
}
