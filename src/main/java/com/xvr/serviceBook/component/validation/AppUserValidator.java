package com.xvr.serviceBook.component.validation;

import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.AppUserService;
import com.xvr.serviceBook.service.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Deprecated
public class AppUserValidator implements Validator {

    private final AppUserService userDetailService;

    @Autowired
    public AppUserValidator(AppUserService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == AppUserForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
