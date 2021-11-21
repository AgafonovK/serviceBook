package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "web/appusers")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public String getAppUsers(Model model) {
        List<AppUser> userForms = appUserService.findAllAppUsers();
        return "";
    }

    //TODO
    @GetMapping(value = "/{id}")
    public String getAppUserById(@RequestParam("id") Long id) {
        System.out.println(id);
        return String.valueOf(id);
    }

    @PostMapping
    public String saveAppUser(@Validated @ModelAttribute(value = "appUserForm") AppUserForm appUserForm,
                              BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes,
                              Model model) {
        if (!appUserForm.getPassword().equals(appUserForm.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("errorMessage", "Пароли не идентичны");
            return "redirect:/create-user";
        }
        System.out.println("REGISTER " + appUserForm.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("EROR " + bindingResult.getFieldError());
            redirectAttributes.addFlashAttribute("errorMessage", "Error: проверьте форму");
            return "redirect:/create-user";
        } else {
            try {
                appUserService.saveUser(AppUserServiceDto.builder()
                        .userName(appUserForm.getUserName())
                        .password(appUserForm.getPassword())
                        .enabled(appUserForm.isEnabled())
                        .build());
                System.out.println("SAVE");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Error: " + e.getMessage());
                return "registerPage";
            }
            redirectAttributes.addFlashAttribute("appUserForm", appUserForm);
        }
        return "redirect:/web/appusers/user-add-successful";
    }

    @RequestMapping(value = "/user-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(Model model) {
        return "registerSuccessfulPage";
    }
}
