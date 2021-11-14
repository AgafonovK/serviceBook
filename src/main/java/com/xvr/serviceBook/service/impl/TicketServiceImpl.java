package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.repository.TicketRepository;
import com.xvr.serviceBook.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
