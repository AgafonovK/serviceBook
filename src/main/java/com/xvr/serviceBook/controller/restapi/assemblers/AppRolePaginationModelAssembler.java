package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.AppRoleModelRepresentation;
import com.xvr.serviceBook.entity.AppRole;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AppRolePaginationModelAssembler implements RepresentationModelAssembler<AppRole, AppRoleModelRepresentation> {

    //TODO

    @Override
    public AppRoleModelRepresentation toModel(AppRole entity) {
        return null;
    }

    @Override
    public CollectionModel<AppRoleModelRepresentation> toCollectionModel(Iterable<? extends AppRole> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
