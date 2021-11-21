package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.impl.WorkerServiceImpl;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import com.xvr.serviceBook.service.servicedto.DepartmentServiceDto;
import com.xvr.serviceBook.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "We waiting you.");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);

        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "loginPage";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "hi " + principal.getName() + "<br> You do not have permission to access this page! " +
                    loginedUser.getAuthorities();
            model.addAttribute("message", message);

        }
        return "403Page";
    }


    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        AppUserForm appUserForm = new AppUserForm();
        model.addAttribute("title", "Application Users List");
        if (appUserForm.getUserName() == null) {
            model.addAttribute("appUserForm", appUserForm);
        }else {
            model.addAttribute("appUSerForm", appUserForm);
            model.addAttribute("ok", true);
        }
        return "registerPage";
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("UserName " + userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }


}
