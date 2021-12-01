package com.xvr.serviceBook.controller.webapi;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.AppUser;
import com.xvr.serviceBook.entity.enums.PositionRole;
import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.form.AppUserRegistrationForm;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.impl.AppRoleServiceImpl;
import com.xvr.serviceBook.service.servicedto.AppUserServiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/web/appusers")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppRoleServiceImpl appRoleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService appUserService, AppRoleServiceImpl appRoleService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.appRoleService = appRoleService;
        this.modelMapper = modelMapper;
    }
    //TODO Mapping form
    @GetMapping
    public String getAppUsers(@PageableDefault(size = 5) Pageable pageRequest,
                              Model model) {
        Page<AppUser> appUsers = appUserService.findAllAppUsersPaginated(pageRequest);
        List<AppUserForm> appUserForms = appUsers.get()
                .map(appUser -> modelMapper.map(appUser, AppUserForm.class))
                .collect(Collectors.toList());
        Page<AppUserForm> appUserFormPage = new PageImpl(appUserForms, pageRequest, appUserForms.size());
        model.addAttribute("appUsers", appUsers);
        return "users/usersPage";
    }

    //TODO
    @GetMapping(value = "/{id}")
    public String getAppUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("AppUser", appUserService.findAppUserById(id));
        return String.valueOf(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveAppUser(@Validated @ModelAttribute(value = "appUserRegistrationForm") AppUserRegistrationForm appUserRegistrationForm,
                              BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes,
                              Model model) {
        if (!appUserRegistrationForm.getPassword().equals(appUserRegistrationForm.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Пароли не идентичны");
            return "redirect:/create-user";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: проверьте форму");
            return "redirect:/create-user";
        } else {
            try {
                appUserService.saveUser(AppUserServiceDto.builder()
                        .userName(appUserRegistrationForm.getUserName())
                        .password(appUserRegistrationForm.getPassword())
                        .enabled(appUserRegistrationForm.isEnabled())
                        .appRole(appUserRegistrationForm.getAppRole())
                        .build());
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Error: " + e.getMessage());
                return "registerPage";
            }
            redirectAttributes.addFlashAttribute("appUserRegistrationForm", appUserRegistrationForm);
        }
        return "redirect:/web/appusers/user-add-successful";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        AppUserRegistrationForm appUserRegistrationForm = new AppUserRegistrationForm();
        Page<AppRole> appRoles = appRoleService.findAll(PageRequest.of(0, 5));
        model.addAttribute("title", "Create User");
        model.addAttribute("appUserRegistrationForm", appUserRegistrationForm);
        model.addAttribute("appRoles", PositionRole.values());

        return "registerPage";
    }

    @RequestMapping(value = "/user-add-successful", method = RequestMethod.GET)
    public String viewDepartmentAddSuccessful(Model model) {
        return "registerSuccessfulPage";
    }
}
