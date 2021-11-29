package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "department", collectionRelation = "departments")
public class DepartmentModelRepresentation extends RepresentationModel<DepartmentModelRepresentation> {

    private final Long id;
    private final String name;
    private CollectionModel<TicketModelRepresentation> departmentTickets;

}
