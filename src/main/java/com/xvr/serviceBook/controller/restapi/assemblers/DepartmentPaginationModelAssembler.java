package com.xvr.serviceBook.controller.restapi.assemblers;

import com.xvr.serviceBook.controller.restapi.DepartmentControllerApi;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.DepartmentRepresentation;
import com.xvr.serviceBook.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DepartmentPaginationModelAssembler implements RepresentationModelAssembler<Department, DepartmentRepresentation> {

    private final TicketPaginationModelAssembler ticketPaginationModelAssembler;

    @Autowired
    public DepartmentPaginationModelAssembler(TicketPaginationModelAssembler ticketPaginationModelAssembler) {
        this.ticketPaginationModelAssembler = ticketPaginationModelAssembler;
    }

    @Override
    public DepartmentRepresentation toModel(Department department) {
        DepartmentRepresentation departmentRepresentation = buildDepartmentRepresentation(department);
        departmentRepresentation.add(WebMvcLinkBuilder.linkTo(methodOn(DepartmentControllerApi.class).getAllDepartments(PageRequest.of(0,5))).withSelfRel());
        departmentRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withRel("department"));
        departmentRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentTickets(PageRequest.of(0,5), department.getId())).withRel("department_tickets"));
        departmentRepresentation.add(linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.getId())).withRel("delete_department"));

        return departmentRepresentation;
    }

    private DepartmentRepresentation buildDepartmentRepresentation(Department department){
        return DepartmentRepresentation.builder()
                .id(department.getId())
                .name(department.getName())
                .departmentTickets(ticketPaginationModelAssembler.toCollectionModel(department.getTickets()))
                .build();
    }
}
