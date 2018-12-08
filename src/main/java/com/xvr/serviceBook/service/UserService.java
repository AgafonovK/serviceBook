package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.User;


public interface UserService {


    User loadUserByUsername(String userName);

}
