package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.repository.StatusTicketRepository;
import com.xvr.serviceBook.service.StatusTicketService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusTicketServiceImpl implements StatusTicketService {

    private final StatusTicketRepository statusTicketRepository;

    @Autowired
    public StatusTicketServiceImpl(StatusTicketRepository statusTicketRepository) {
        this.statusTicketRepository = statusTicketRepository;
    }

    @Override
    public List<StatusTicket> findAllStatusTicketList() {
        return statusTicketRepository.findAll();
    }
}
