package com.xvr.serviceBook.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DTO {
    //https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/
}
