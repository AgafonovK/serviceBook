package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.entity.enums.PositionRole;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "web/appusers")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public String getAppUsers(@PageableDefault(size = 5) Pageable pageRequest, Model model) {
        Page<AppUser> appUsers = appUserService.findAllAppUsersPaginated(pageRequest);
        model.addAttribute("AppUsers", appUsers);
        return "/";
    }

    //TODO
    @GetMapping(value = "/{id}")
    public String getAppUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("AppUser", appUserService.findAppUserById(id));
        return String.valueOf(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveAppUser(@Validated @ModelAttribute(value = "appUserForm") AppUserForm appUserForm,
                              BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes,
                              Model model) {
        if (!appUserForm.getPassword().equals(appUserForm.getConfirmPassword())) {
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        AppUserForm appUserForm = new AppUserForm();
        model.addAttribute("title", "Application Users List");
        if (appUserForm.getUserName() == null) {
            model.addAttribute("appUserForm", appUserForm);
        } else {
            model.addAttribute("appUSerForm", appUserForm);
            model.addAttribute("ok", true);
            //TODO check list in html page
            model.addAttribute("appRoles", PositionRole.values());
        }
        return "registerPage";
    }

    @RequestMapping(value = "/user-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(Model model) {
        return "registerSuccessfulPage";
    }
}
