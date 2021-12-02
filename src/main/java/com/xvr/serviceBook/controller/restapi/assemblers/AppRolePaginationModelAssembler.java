package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.AppRoleControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AppRolePaginationModelAssembler implements RepresentationModelAssembler<AppRole, AppRoleModelRepresentation> {

    //TODO

    @Override
    public AppRoleModelRepresentation toModel(AppRole appRole) {

        return AppRoleModelRepresentation.builder()
                .appRoleId(appRole.getAppRoleId())
                .roleName(appRole.getRoleName())
                .build().add(linkTo(methodOn(AppRoleControllerApi.class).getRoleById(appRole.getAppRoleId())).withSelfRel());
    }

    @Override
    public CollectionModel<AppRoleModelRepresentation> toCollectionModel(Iterable<? extends AppRole> appRoles) {
        return RepresentationModelAssembler.super.toCollectionModel(appRoles);
    }
}
