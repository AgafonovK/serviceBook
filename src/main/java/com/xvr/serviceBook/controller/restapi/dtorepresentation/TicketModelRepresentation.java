package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "ticket", collectionRelation = "tickets")
public class TicketModelRepresentation extends RepresentationModel<TicketModelRepresentation> {

    private Long id;

    private String ticketDescription;

    private LocalDate startDateTicket;

    private LocalDate endDateTicket;

    @JsonProperty("statusTicket")
    private String statusTicket;

    /*
    private PriorityTicket priorityTicket;


    private Set<Worker> workers;*/

   @JsonProperty("clientDepartment")
   private String clientDepartmentRepresentation;
    //private Equipment equipment;
}
