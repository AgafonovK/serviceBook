package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "department", collectionRelation = "departments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentModelRepresentation extends RepresentationModel<DepartmentModelRepresentation> {

    private Long id;
    private String name;
    private Set<TicketModelRepresentation> departmentTickets;

}
