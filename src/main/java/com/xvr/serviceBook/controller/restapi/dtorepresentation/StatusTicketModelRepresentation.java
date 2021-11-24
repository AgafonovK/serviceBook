package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public class StatusTicketModelRepresentation extends RepresentationModel<StatusTicketModelRepresentation> {

    private Long id;

    private String statusName;

    private CollectionModel<TicketModelRepresentation> statusTickets;
}
