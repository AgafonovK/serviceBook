package com.xvr.serviceBook.validation;

import com.xvr.serviceBook.form.AppUserForm;
import com.xvr.serviceBook.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == AppUserForm.class;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
