package com.xvr.serviceBook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/welcome"},method = RequestMethod.POST)
    public String welcomePage(Model model){
        model.addAttribute("title", "Welcome");
        model.addAttribute("message","This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String adminPage(Model model, Principal principal){
        User loginedUser = (User) ((Authentication)principal).getPrincipal();

        //String userInfo =
        return "";
    }
}
