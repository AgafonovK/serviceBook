package com.xvr.serviceBook;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.service.AppRoleService;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.impl.AppUserServiceImpl;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

import java.util.stream.Collectors;

@SpringBootTest
@Commit
public class EventTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppRoleService appRoleService;

    @Test
    public void test(){
        appUserService.saveUser(AppUserServiceDto.builder()
                .userName("TestUserName")
                .enabled(true)
                .password("qwerty")
                .appRole(appRoleService.findAll(PageRequest.of(0,5)).get().collect(Collectors.toSet()))
                .build());
        //assert ();
    }

}
