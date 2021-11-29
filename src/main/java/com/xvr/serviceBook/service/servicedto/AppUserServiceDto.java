package com.xvr.serviceBook.service.servicedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AppUserServiceDto {

    private String userName;

    private String password;

    private boolean enabled;


}
