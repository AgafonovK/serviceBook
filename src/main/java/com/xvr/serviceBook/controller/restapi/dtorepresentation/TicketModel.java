package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xvr.serviceBook.entity.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "ticket", collectionRelation = "tickets")
public class TicketModel extends RepresentationModel<TicketModel> {

    private Long id;

    private String ticketDescription;

    private LocalDate startDateTicket;

    private LocalDate endDateTicket;

   /* private StatusTicket statusTicket;

    private PriorityTicket priorityTicket;


    private Set<Worker> workers;*/

   @JsonProperty("clientDepartment")
   private String clientDepartmentRepresentation;
    //private Equipment equipment;
}
