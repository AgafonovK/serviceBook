package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.User;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.UserRepository;
import com.xvr.serviceBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
   // @Autowired
    //private AppRoleRepository appRoleRepository;


    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userRepository.findUserAccount(userName);
        if (user==null){
            System.out.println("User not found" + user);
            throw new UsernameNotFoundException("USer "+
                   userName + "was ot found ");
        }
       // List<String> roleNames = this.appRoleRepository.
        return null;
    }



}
