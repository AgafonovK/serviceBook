package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.AppRoleControllerApi;
import com.xvr.serviceBook.controller.restapi.AppUserControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class AppRolePaginationModelAssembler implements RepresentationModelAssembler<AppRole, AppRoleModelRepresentation> {

    //TODO

    @Override
    public AppRoleModelRepresentation toModel(AppRole entity) {

        return AppRoleModelRepresentation.builder()
                .appRoleId(entity.getAppRoleId())
                .roleName(entity.getRoleName())
                .build().add(linkTo(methodOn(AppRoleControllerApi.class).getRoleById(entity.getAppRoleId())).withSelfRel());
    }

    @Override
    public CollectionModel<AppRoleModelRepresentation> toCollectionModel(Iterable<? extends AppRole> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
