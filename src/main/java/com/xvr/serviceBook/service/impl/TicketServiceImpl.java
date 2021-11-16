package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.service.TicketService;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
    }
}
