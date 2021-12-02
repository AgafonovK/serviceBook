package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.DepartmentControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentPaginationModelAssembler implements RepresentationModelAssembler<Department, DepartmentModelRepresentation> {

    private final ModelMapper modelMapper;

    public DepartmentPaginationModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentModelRepresentation toModel(Department department) {
        DepartmentModelRepresentation departmentModelRepresentation = buildDepartmentRepresentation(department);
        departmentModelRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withSelfRel());
        return departmentModelRepresentation;
    }

    private DepartmentModelRepresentation buildDepartmentRepresentation(Department department) {
        return DepartmentModelRepresentation.builder()
                .id(department.getId())
                .name(department.getName())
                .departmentTickets(toTicketRoleModel(department.getTickets()))
                .build();
    }

    private Set<TicketModelRepresentation> toTicketRoleModel(Set<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return Collections.emptySet();
        }
        return tickets.stream().map(ticket -> {
            return TicketModelRepresentation.builder()
                    .id(ticket.getId())
                    .startDateTicket(ticket.getStartDateTicket())
                    .endDateTicket(ticket.getEndDateTicket())
                    .statusTicket(toStatusTicketModel(ticket.getStatusTicket()))
                    .ticketDescription(ticket.getTicketDescription())
                    //.clientDepartmentRepresentation()
                    .statusTicket(toStatusTicketModel(ticket.getStatusTicket()))
                    .build();
        }).collect(Collectors.toSet());
    }

    private StatusTicketModelRepresentation toStatusTicketModel(StatusTicket statusTicket) {
        return StatusTicketModelRepresentation.builder()
                    .id(statusTicket.getId())
                    .statusName(statusTicket.getStatusName())
                    .build();
    }
    @Override
    public CollectionModel<DepartmentModelRepresentation> toCollectionModel(Iterable<? extends Department> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(0, 5))).withSelfRel());

    }
}
