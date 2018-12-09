package com.xvr.serviceBook.utils;

import org.springframework.security.core.userdetails.User;

public class WebUtils {

    public static String toString(User user){
        StringBuilder sb = new StringBuilder();
        sb.append("USerName:").append(user.getUsername());
        return "";
    }
}
