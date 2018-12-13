package com.xvr.serviceBook.controller;

import com.xvr.serviceBook.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/welcome"},method = RequestMethod.GET)
    public String welcomePage(Model model){
        model.addAttribute("title", "Welcome");
        model.addAttribute("message","This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal){
        User loginedUser = (User) ((Authentication)principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userinfo", userInfo);
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "loginPage";
    }



}
