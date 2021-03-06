package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.AppUserRepository;
import com.xvr.serviceBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;

    private final AppRoleRepository appRoleRepository;

    @Autowired
    public UserDetailServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        AppUser savedAppUser = appUserRepository.saveAndFlush(appUser);
        return savedAppUser;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("username " + userName);
        AppUser appUser = this.appUserRepository.findUserAccount(userName);
        if (appUser==null){
            System.out.println("AppUser not found " + appUser);
            throw new UsernameNotFoundException("User "+
                    userName + "was not found ");
        }
        System.out.println("Found user " + appUser.getUserName() + " idUser " + appUser.getId());
        //Role USER, ROLE ADMIN
        List<String> roleNames = this.appRoleRepository.getRoleNames(appUser.getId());
        System.out.println("size= " + roleNames.size());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        if (roleNames!=null){
            for (String role: roleNames ){
                System.out.println("role = " + role);
                GrantedAuthority authority =new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        }else {
            System.out.println("roleNames = null");
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), appUser.getEncryptedPassword(),grantedAuthorityList);
        return userDetails;
    }

}
