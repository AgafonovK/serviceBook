package com.xvr.serviceBook.controller;

import com.sun.xml.internal.bind.v2.TODO;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.form.WorkerForm;
import com.xvr.serviceBook.repository.WorkerRepository;
import com.xvr.serviceBook.service.WorkerService;
import com.xvr.serviceBook.service.impl.DepartmentServiceImpl;
import com.xvr.serviceBook.service.impl.WorkerServiceImpl;
import com.xvr.serviceBook.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {


    private final WorkerServiceImpl workerService;

    private final DepartmentServiceImpl departmentService;


    @Autowired
    public MainController(WorkerServiceImpl workerService, DepartmentServiceImpl departmentService) {
        this.workerService = workerService;
        this.departmentService = departmentService;
    }

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

        model.addAttribute("userInfo", userInfo);


        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model){
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied (Model model,Principal principal){
        if (principal!=null){
            User loginedUser = (User)((Authentication)principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }

    //TODO Show register page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model){
        AppUserForm form = new AppUserForm();
        model.addAttribute("appUserForm", form);
        return "registerPage";
    }

    @RequestMapping(value = "/registerSuccessful")
    public String viewRegisterSuccessful(Model model){
        return "registerSuccessfulPage";
    }


    @RequestMapping (value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal){
        String userName = principal.getName();
        User loginedUSer = (User) ((Authentication)principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUSer);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }



}
