package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.AppRoleControllerApi;
import com.xvr.serviceBook.controller.restapi.AppUserControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppUserModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppUserPaginationModelAssembler implements RepresentationModelAssembler<AppUser, AppUserModelRepresentation> {


    //TODO
    @Override
    public AppUserModelRepresentation toModel(AppUser appUser) {


        return AppUserModelRepresentation.builder()
                .id(appUser.getUserId())
                .userName(appUser.getUserName())
                .enabled(intToBoolean(appUser.getEnabled()))
                .appRole(toAppRoleModels(appUser.getAppRole()))
                .build().add(linkTo(methodOn(AppUserControllerApi.class).getUserById(appUser.getUserId())).withSelfRel());

    }

    @Override
    public CollectionModel<AppUserModelRepresentation> toCollectionModel(Iterable<? extends AppUser> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(AppUserControllerApi.class).getUsers(PageRequest.of(0, 5))).withSelfRel());
    }

    private Set<AppRoleModelRepresentation> toAppRoleModels(Set<AppRole> appRoles) {
        if (appRoles.isEmpty())
            return Collections.emptySet();

        return appRoles.stream()
                .map(appRole -> AppRoleModelRepresentation.builder()
                        .appRoleId(appRole.getAppRoleId())
                        .roleName(appRole.getRoleName())
                        .build()
                        .add(linkTo(methodOn(AppRoleControllerApi.class).getRoleById(appRole.getAppRoleId())).withSelfRel()))
                .collect(Collectors.toSet());
    }

    private boolean intToBoolean(int input) {
        if ((input == 0) || (input == 1)) {
            return input != 0;
        } else {
            throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
        }
    }
}
