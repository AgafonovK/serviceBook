package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserDetailServiceImpl userDetailService;

    public UserController(UserDetailServiceImpl userDetailService){
        this.userDetailService=userDetailService;
    }
    @GetMapping
    public String getUsers(Model model){
        List<AppUser> userForms = userDetailService.getAppUsers();

        return "";
    }

    //TODO
    @GetMapping(value = "{id}")
    public String getUserById(@RequestParam("id") Long id){
        System.out.println(id);
        return String.valueOf(id);
    }
    @PostMapping(value = "/")
    public ResponseEntity<Object> createUser(@ModelAttribute AppUser appUserForm) {
        System.out.println(appUserForm.toString());
        AppUser appUser = new AppUser();
        return ResponseEntity.ok().body(appUser.getUserName());
    }
}
