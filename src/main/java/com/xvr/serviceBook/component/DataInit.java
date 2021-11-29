package com.xvr.serviceBook.component;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class DataInit implements ApplicationRunner {

    private AppUserRepository appUserRepository;

    @Autowired
    public DataInit(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //create and save entity
    }
}
