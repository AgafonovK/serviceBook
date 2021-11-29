package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.DepartmentControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentModelRepresentation;
import com.xvr.serviceBook.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentPaginationModelAssembler implements RepresentationModelAssembler<Department, DepartmentModelRepresentation> {

    private final TicketPaginationModelAssembler ticketPaginationModelAssembler;

    @Autowired
    public DepartmentPaginationModelAssembler(TicketPaginationModelAssembler ticketPaginationModelAssembler) {
        this.ticketPaginationModelAssembler = ticketPaginationModelAssembler;
    }

    @Override
    public DepartmentModelRepresentation toModel(Department department) {
        DepartmentModelRepresentation departmentModelRepresentation = buildDepartmentRepresentation(department);
        departmentModelRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(0,5))).withSelfRel());
        departmentModelRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withRel("department"));
        departmentModelRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentTickets(PageRequest.of(0,5), department.getId())).withRel("department_tickets"));

        return departmentModelRepresentation;
    }

    private DepartmentModelRepresentation buildDepartmentRepresentation(Department department){
        return DepartmentModelRepresentation.builder()
                .id(department.getId())
                .name(department.getName())
                .departmentTickets(ticketPaginationModelAssembler.toCollectionModel(department.getTickets()))
                .build();
    }

    @Override
    public CollectionModel<DepartmentModelRepresentation> toCollectionModel(Iterable<? extends Department> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
