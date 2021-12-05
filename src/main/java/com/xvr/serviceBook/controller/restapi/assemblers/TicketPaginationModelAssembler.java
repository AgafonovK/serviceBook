package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.StatusTicketControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.entity.Ticket;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TicketPaginationModelAssembler implements RepresentationModelAssembler<Ticket, TicketModelRepresentation> {

    @Override
    public TicketModelRepresentation toModel(Ticket entity) {

        TicketModelRepresentation ticketModelRepresentation = TicketModelRepresentation.builder()
                .id(entity.getId())
                .startDateTicket(entity.getStartDateTicket())
                .endDateTicket(entity.getEndDateTicket())
                .statusTicket(toStatusTicketModel(entity.getStatusTicket()))
                .ticketDescription(entity.getTicketDescription())
                //TODO create toClientDepartment
                //.clientDepartmentRepresentation(entity.getClientDepartment().getName())
                .build();
        return ticketModelRepresentation;
    }

    @Override
    public CollectionModel<TicketModelRepresentation> toCollectionModel(Iterable<? extends Ticket> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    private StatusTicketModelRepresentation toStatusTicketModel(StatusTicket statusTicket){
        return StatusTicketModelRepresentation.builder()
                .id(statusTicket.getId())
                .statusName(statusTicket.getStatusName())
                .build()
                .add(linkTo(methodOn(StatusTicketControllerApi.class).findAllStatusTickets(PageRequest.of(0,5))).withSelfRel());
    }
}
