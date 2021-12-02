package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.xvr.serviceBook.entity.AppUser;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.catalina.LifecycleState;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)

public class AppRoleModelRepresentation extends RepresentationModel<AppRoleModelRepresentation> {

    private Long appRoleId;

    private String roleName;

    private List<AppUserModelRepresentation> appUsers;
}
