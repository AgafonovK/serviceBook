package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.TicketPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.TicketModelRepresentation;
import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ExposesResourceFor(Ticket.class)
@RequestMapping(value = "/rest/tickets")
public class TicketControllerApi {

    private final TicketService ticketService;
    private final PagedResourcesAssembler<Ticket> ticketPagedResourcesAssembler;
    private final TicketPaginationModelAssembler ticketPaginationModelAssembler;

    @Autowired
    public TicketControllerApi(TicketService ticketService, PagedResourcesAssembler<Ticket> ticketPagedResourcesAssembler,
                               TicketPaginationModelAssembler ticketPaginationModelAssembler) {
        this.ticketService = ticketService;
        this.ticketPagedResourcesAssembler = ticketPagedResourcesAssembler;
        this.ticketPaginationModelAssembler = ticketPaginationModelAssembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<TicketModelRepresentation>> getAllTickets(@PageableDefault(size = 5) Pageable pageRequest) {

        Page<Ticket> ticketsPaginated = ticketService.findAllTicketsPaginated(pageRequest);

        PagedModel<TicketModelRepresentation> model = ticketPagedResourcesAssembler.toModel(ticketsPaginated,ticketPaginationModelAssembler);
        return !ticketsPaginated.isEmpty()
                ? ResponseEntity.ok(model)
                : ResponseEntity.notFound().build();
    }


}
