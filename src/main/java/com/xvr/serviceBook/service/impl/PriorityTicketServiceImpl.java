package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.PriorityTicket;
import com.xvr.serviceBook.repository.PriorityTicketRepository;
import com.xvr.serviceBook.service.PriorityTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityTicketServiceImpl implements PriorityTicketService {

    private final PriorityTicketRepository priorityTicketRepository;

    @Autowired
    public PriorityTicketServiceImpl(PriorityTicketRepository priorityTicketRepository) {
        this.priorityTicketRepository = priorityTicketRepository;
    }

    @Override
    public List<PriorityTicket> findAllPriorityTicketList() {
        return priorityTicketRepository.findAll();
    }
}
