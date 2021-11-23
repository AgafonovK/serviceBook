package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModel;
import com.xvr.serviceBook.entity.Ticket;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class TicketPaginationModelAssembler implements RepresentationModelAssembler<Ticket, TicketModel> {

    @Override
    public TicketModel toModel(Ticket entity) {

        TicketModel ticketModel = TicketModel.builder()
                .id(entity.getId())
                .startDateTicket(entity.getStartDateTicket())
                .endDateTicket(entity.getEndDateTicket())
                .ticketDescription(entity.getTicketDescription())
                .clientDepartmentRepresentation(entity.getClientDepartment().getName())
                .build();
        return ticketModel;
    }

    @Override
    public CollectionModel<TicketModel> toCollectionModel(Iterable<? extends Ticket> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
