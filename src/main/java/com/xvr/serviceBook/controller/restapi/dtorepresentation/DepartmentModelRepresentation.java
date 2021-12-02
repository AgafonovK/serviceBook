package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "department", collectionRelation = "departments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentModelRepresentation extends RepresentationModel<DepartmentModelRepresentation> {

    private final Long id;
    private final String name;
    private Set<TicketModelRepresentation> departmentTickets;

}
