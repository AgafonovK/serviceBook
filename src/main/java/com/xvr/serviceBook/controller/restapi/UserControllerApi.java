package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.UserService;
import com.xvr.serviceBook.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/users")
public class UserControllerApi {

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public UserControllerApi(UserDetailServiceImpl userDetailService){
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public ResponseEntity<AppUserForm> getUsers(){

       return ResponseEntity.ok(new AppUserForm());
    }
}
