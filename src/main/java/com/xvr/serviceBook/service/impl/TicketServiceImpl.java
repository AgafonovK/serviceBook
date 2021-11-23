package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.event.TicketCreateEvent;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.service.TicketService;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.ticketRepository = ticketRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Page<Ticket> findAllTicketsPaginated(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public List<Ticket> findAllTicketsList() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional
    public void save(TicketServiceDto ticketServiceDto) {

        Ticket ticket = Ticket.builder()
                .endDateTicket(ticketServiceDto.getEndDateTicket())
                .startDateTicket(ticketServiceDto.getStartDateTicket())
                .ticketDescription(ticketServiceDto.getTicketDescription())
                .clientDepartment(ticketServiceDto.getClientDepartment())
                .equipment(ticketServiceDto.getEquipment())
                .priorityTicket(ticketServiceDto.getPriorityTicket())
                .statusTicket(ticketServiceDto.getStatus())
                .workers(ticketServiceDto.getWorker())
                .build();
        ticketRepository.save(ticket);
        TicketCreateEvent ticketCreateEvent = new TicketCreateEvent(this, ticket);
        applicationEventPublisher.publishEvent(ticketCreateEvent);
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    //TODO
    @Override
    public Page<Ticket> findTicketsByDepartmentId(Pageable pageable, Long departmentId) {
        return null;
    }
}
