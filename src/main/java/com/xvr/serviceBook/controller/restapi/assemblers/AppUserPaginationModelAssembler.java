package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.AppUserControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppUserModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import com.xvr.serviceBook.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppUserPaginationModelAssembler implements RepresentationModelAssembler<AppUser, AppUserModelRepresentation> {

    private final AppRolePaginationModelAssembler appRolePaginationModelAssembler;

    @Autowired
    public AppUserPaginationModelAssembler(AppRolePaginationModelAssembler appRolePaginationModelAssembler) {
        this.appRolePaginationModelAssembler = appRolePaginationModelAssembler;
    }

    //TODO
    @Override
    public AppUserModelRepresentation toModel(AppUser entity) {
        System.out.println("ROLES " + entity.getAppRole().size());
        return AppUserModelRepresentation.builder()
                .id(entity.getUserId())
                .userName(entity.getUserName())
                .enabled(intToBoolean(entity.getEnabled()))
                .appRole(appRolePaginationModelAssembler.toCollectionModel(entity.getAppRole()))
                .build().add(linkTo(methodOn(AppUserControllerApi.class).getUserById(entity.getUserId())).withSelfRel());

    }

    @Override
    public CollectionModel<AppUserModelRepresentation> toCollectionModel(Iterable<? extends AppUser> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }


    private boolean intToBoolean(int input) {
        if((input==0)||(input==1)) {
            return input!=0;
        }else {
            throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
        }
    }
}
