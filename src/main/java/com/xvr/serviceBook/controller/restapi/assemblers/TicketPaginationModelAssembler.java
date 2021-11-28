package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.Ticket;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TicketPaginationModelAssembler implements RepresentationModelAssembler<Ticket, TicketModelRepresentation> {

    @Override
    public TicketModelRepresentation toModel(Ticket entity) {

        TicketModelRepresentation ticketModelRepresentation = TicketModelRepresentation.builder()
                .id(entity.getId())
                .startDateTicket(entity.getStartDateTicket())
                .endDateTicket(entity.getEndDateTicket())
                .statusTicket(entity.getStatusTicket().getStatusName())
                .ticketDescription(entity.getTicketDescription())
                .clientDepartmentRepresentation(entity.getClientDepartment().getName())
                .build();
        return ticketModelRepresentation;
    }

    @Override
    public CollectionModel<TicketModelRepresentation> toCollectionModel(Iterable<? extends Ticket> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
