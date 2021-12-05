package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.StatusTicket;
import com.xvr.serviceBook.repository.StatusTicketRepository;
import com.xvr.serviceBook.service.StatusTicketService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<StatusTicket> findAllStatusTicketPageable(Pageable pageable) {
        return statusTicketRepository.findAll(pageable);
    }

    @Override
    public List<StatusTicket> findAllStatusTicketList() {
        return statusTicketRepository.findAll();
    }
}
