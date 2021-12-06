package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.DepartmentControllerApi;
import com.xvr.serviceBook.controller.restapi.StatusTicketControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentPaginationModelAssembler implements RepresentationModelAssembler<Department, DepartmentModelRepresentation> {

    private final ModelMapper modelMapper;
    private final StatusTicketPaginationModelAssembler statusTicketPaginationModelAssembler;
    public static final Logger logger = LoggerFactory.getLogger(DepartmentPaginationModelAssembler.class);

    public DepartmentPaginationModelAssembler(ModelMapper modelMapper, StatusTicketPaginationModelAssembler statusTicketPaginationModelAssembler) {
        this.modelMapper = modelMapper;
        this.statusTicketPaginationModelAssembler = statusTicketPaginationModelAssembler;
    }

    @Override
    public DepartmentModelRepresentation toModel(Department department) {
        var departmentModelRepresentation = buildDepartmentRepresentation(department);
        logger.info(departmentModelRepresentation.toString());
        return departmentModelRepresentation;
    }

    private DepartmentModelRepresentation buildDepartmentRepresentation(Department department) {
        return DepartmentModelRepresentation.builder()
                .id(department.getId())
                .name(department.getName())
                .departmentTickets(toTicketRoleModel(department.getTickets()))
                .build()
                .add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withSelfRel());
    }

    private Set<TicketModelRepresentation> toTicketRoleModel(Set<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return Collections.emptySet();
        }
        return tickets.stream().map(ticket -> TicketModelRepresentation.builder()
                .id(ticket.getId())
                .startDateTicket(ticket.getStartDateTicket())
                .endDateTicket(ticket.getEndDateTicket())
                .statusTicket(toStatusTicketModel(ticket.getStatusTicket()))
                .ticketDescription(ticket.getTicketDescription())
                //.clientDepartmentRepresentation()
                .build()
        ).collect(Collectors.toSet());

    }

    private StatusTicketModelRepresentation toStatusTicketModel(StatusTicket statusTicket) {
        return StatusTicketModelRepresentation.builder()
                .id(statusTicket.getId())
                .statusName(statusTicket.getStatusName())
                .build()
                .add(linkTo(methodOn(StatusTicketControllerApi.class).findAllStatusTickets(PageRequest.of(0, 5))).withSelfRel());
    }
    @Override
    public CollectionModel<DepartmentModelRepresentation> toCollectionModel(Iterable<? extends Department> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(0, 5))).withSelfRel());

    }
}
