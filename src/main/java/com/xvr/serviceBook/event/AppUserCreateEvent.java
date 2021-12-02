package com.xvr.serviceBook.event;

import com.xvr.serviceBook.entity.AppUser;
import lombok.Getter;

@Getter
public class AppUserCreateEvent {
    public AppUser appUser;

    public AppUserCreateEvent(Object object, AppUser appUser) {
        this.appUser = appUser;
    }

}
