package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.entity.StatusTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class StatusTicketPaginationModelAssembler implements RepresentationModelAssembler<StatusTicket, StatusTicketModelRepresentation> {

    private final TicketPaginationModelAssembler ticketPaginationModelAssembler;

    @Autowired
    public StatusTicketPaginationModelAssembler(TicketPaginationModelAssembler ticketPaginationModelAssembler) {
        this.ticketPaginationModelAssembler = ticketPaginationModelAssembler;
    }

    @Override
    public StatusTicketModelRepresentation toModel(StatusTicket entity) {
        return StatusTicketModelRepresentation.builder()
                .id(entity.getId())
                .statusName(entity.getStatusName())
                //.statusTickets(ticketPaginationModelAssembler.toCollectionModel(entity.getTickets()))
                .build();
    }

    @Override
    public CollectionModel<StatusTicketModelRepresentation> toCollectionModel(Iterable<? extends StatusTicket> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
