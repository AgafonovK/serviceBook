package com.xvr.serviceBook.controller.restapi;

import com.xvr.serviceBook.controller.restapi.assemblers.StatusTicketPaginationModelAssembler;
import com.xvr.serviceBook.controller.restapi.dtorepresentation.StatusTicketModelRepresentation;
import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.service.StatusTicketService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@ExposesResourceFor(StatusTicket.class)
@RequestMapping(value = "/rest/statusTickets")
public class StatusTicketControllerApi {

    private final StatusTicketService statusTicketService;
    private final PagedResourcesAssembler<StatusTicket> pagedResourcesAssembler;
    private final StatusTicketPaginationModelAssembler statusTicketPaginationModelAssembler;
    public StatusTicketControllerApi(StatusTicketService statusTicketService, PagedResourcesAssembler<StatusTicket> pagedResourcesAssembler, StatusTicketPaginationModelAssembler statusTicketPaginationModelAssembler) {
        this.statusTicketService = statusTicketService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.statusTicketPaginationModelAssembler = statusTicketPaginationModelAssembler;
    }

    public ResponseEntity<PagedModel<StatusTicketModelRepresentation>> findAllStatusTickets(@PageableDefault(size = 5)Pageable pageable){
        PagedModel<StatusTicketModelRepresentation> statusTicketModelRepresentations =
                pagedResourcesAssembler.toModel(statusTicketService.findAllStatusTicketPageable(pageable),statusTicketPaginationModelAssembler);
        return ResponseEntity.ok(statusTicketModelRepresentations);
    }
}
