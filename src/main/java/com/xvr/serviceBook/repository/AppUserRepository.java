package com.xvr.serviceBook.repository;


import com.xvr.serviceBook.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends JpaRepository <AppUser,Long> {

    //my custom methods fo find userAccount
    @Query("select u from AppUser u where u.userName = :name")
    AppUser findUserAccount(@Param("name") String userName);

}
