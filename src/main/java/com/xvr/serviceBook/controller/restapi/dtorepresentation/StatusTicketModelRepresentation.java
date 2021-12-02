package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusTicketModelRepresentation extends RepresentationModel<StatusTicketModelRepresentation> {

    private Long id;

    private String statusName;

    private CollectionModel<TicketModelRepresentation> statusTickets;
}
