package com.xvr.serviceBook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public String users(){
        return "";
    }

    //TODO
    @GetMapping(value = "{id}")
    public String getUserById(@RequestParam("id") Long id){
        System.out.println(id);
        return String.valueOf(id);
    }
}
