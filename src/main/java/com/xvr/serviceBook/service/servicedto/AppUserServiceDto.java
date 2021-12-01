package com.xvr.serviceBook.service.servicedto;

import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.Worker;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Builder
public class AppUserServiceDto {

    private String userName;

    private String password;

    private boolean enabled;

    private Set<AppRole> appRole;

    private Worker worker;

}
