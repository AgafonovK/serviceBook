package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.AppRoleControllerApi;
import com.xvr.serviceBook.controller.restapi.AppUserControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppUserModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.AppUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppRolePaginationModelAssembler implements RepresentationModelAssembler<AppRole, AppRoleModelRepresentation> {


    @Override
    public AppRoleModelRepresentation toModel(AppRole appRole) {

        return AppRoleModelRepresentation.builder()
                .appRoleId(appRole.getAppRoleId())
                .roleName(appRole.getRoleName())
                .appUsers(toAppUserModels(appRole.getAppUsers()))
                .build()
                .add(linkTo(
                        methodOn(AppRoleControllerApi.class).getRoleById(appRole.getAppRoleId())).withSelfRel());
    }

    @Override
    public CollectionModel<AppRoleModelRepresentation> toCollectionModel(Iterable<? extends AppRole> appRoles) {
        return RepresentationModelAssembler.super.toCollectionModel(appRoles)
                .add(linkTo(methodOn(AppRoleControllerApi.class).getRoles(PageRequest.of(0,5))).withSelfRel());
    }

    private Set<AppUserModelRepresentation> toAppUserModels(Set<AppUser> appUsers) {
        if (appUsers.isEmpty())
            return Collections.emptySet();

        return appUsers.stream()
                .map(appUser -> AppUserModelRepresentation.builder()
                        .id(appUser.getUserId())
                        .userName(appUser.getUserName())
                        .enabled(appUser.getEnabled() == 1)
                        .build()
                        .add(linkTo(methodOn(AppUserControllerApi.class).getUserById(appUser.getUserId())).withSelfRel()))
                .collect(Collectors.toSet());
    }
}
