package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "ticket", collectionRelation = "tickets")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketModelRepresentation extends RepresentationModel<TicketModelRepresentation> {

    private Long id;

    private String ticketDescription;

    private LocalDate startDateTicket;

    private LocalDate endDateTicket;

    @JsonProperty("statusTicket")
    private StatusTicketModelRepresentation statusTicket;

    /*
    private PriorityTicket priorityTicket;


    private Set<Worker> workers;*/

    @JsonProperty("clientDepartment")
    private DepartmentModelRepresentation clientDepartmentRepresentation;

    //private Equipment equipment;
}
