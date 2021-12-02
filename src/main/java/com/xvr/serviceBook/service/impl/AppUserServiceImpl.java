package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.event.AppUserCreateEvent;
import com.xvr.serviceBook.repository.AppRoleRepository;
import com.xvr.serviceBook.repository.AppUserRepository;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import com.xvr.serviceBook.service.servicedto.WorkerServiceDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);


    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationEventPublisher applicationEventPublisher, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppUser> findAllAppUsers() {
        return appUserRepository.findAll();

    }

    @Override
    public Page<AppUser> findAllAppUsersPaginated(Pageable pageable) {

        return appUserRepository.findAll(pageable);
    }

    @Override
    public Optional<AppUser> findAppUserById(Long id){
        return appUserRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveUser(AppUserServiceDto appUserServiceDto) {

        AppUser appUser = AppUser.builder()
                .userName(appUserServiceDto.getUserName())
                .encryptedPassword(bCryptPasswordEncoder.encode(appUserServiceDto.getPassword()))
                .enabled(appUserServiceDto.isEnabled() ? 1 : 0)
                .build();
        appUserRepository.saveAndFlush(appUser);
        applicationEventPublisher.publishEvent(new AppUserCreateEvent(this, appUser));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("Found username: " + userName);
        AppUser appUser = this.appUserRepository.findUserAccount(userName);
        if (appUser == null) {
            logger.warn("AppUser not found! ");
            throw new UsernameNotFoundException("User " +
                    userName + "was not found ");
        }
        logger.info("Found user " + appUser.getUserName() + " idUser " + appUser.getUserId());
        //Role USER, ROLE ADMIN
        List<String> roleNames = this.appRoleRepository.getRoleNames(appUser.getUserId());
        //System.out.println("size= " + roleNames.size());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                //System.out.println("role = " + role);
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        } else {
            logger.warn("Role Names is null");
        }

        return new User(appUser.getUserName(), appUser.getEncryptedPassword(), grantedAuthorityList);
    }

}
