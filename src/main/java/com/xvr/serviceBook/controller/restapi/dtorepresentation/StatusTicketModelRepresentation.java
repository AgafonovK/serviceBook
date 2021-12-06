package com.xvr.serviceBook.controller.restapi.dtorepresentation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatusTicketModelRepresentation extends RepresentationModel<StatusTicketModelRepresentation> {

    private Long id;

    private String statusName;

    private CollectionModel<TicketModelRepresentation> statusTickets;
}
