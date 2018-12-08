package com.xvr.serviceBook.repository;


import com.xvr.serviceBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <User,Long> {

    //my custom methods fo find userAccount
    @Query("select u from User u where u.userName = :name")
    User findUserAccount(@Param("name") String userName);

}
