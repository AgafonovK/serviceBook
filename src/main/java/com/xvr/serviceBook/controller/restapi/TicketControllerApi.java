package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@ExposesResourceFor(Ticket.class)
@RequestMapping(value = "/rest/controllers")
public class TicketControllerApi {

    private final TicketService ticketService;
    private final PagedResourcesAssembler ticketPagedResourcesAssembler;

    @Autowired
    public TicketControllerApi(TicketService ticketService, PagedResourcesAssembler ticketPagedResourcesAssembler) {
        this.ticketService = ticketService;
        this.ticketPagedResourcesAssembler = ticketPagedResourcesAssembler;
    }


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Ticket>>> getAllTickets(@PageableDefault(page = 0, size = 10) Pageable pageRequest) {

        Page<Ticket> departments = ticketService.findAllTicketsPaginated(pageRequest)
                .map(ticket -> {
                    ticket.add(linkTo(methodOn(TicketControllerApi.class).getAllTickets(pageRequest)).withSelfRel());
                    //department.add(linkTo(methodOn(DepartmentControllerApi.class).getDepartmentById(department.getId())).withRel("department"));
                    //department.add(linkTo(methodOn(DepartmentControllerApi.class).saveDepartment())).withRel("create_new_department"));
                    //department.add(linkTo(methodOn(DepartmentControllerApi.class).deleteDepartmentById(department.getId())).withRel("delete_department"));
                    return ticket;
                });
        PagedModel<EntityModel<Ticket>> model = ticketPagedResourcesAssembler
                .toModel(departments);
        return !departments.isEmpty()
                ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }
}
