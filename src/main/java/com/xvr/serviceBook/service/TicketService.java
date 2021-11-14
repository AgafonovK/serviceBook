package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {

    Page<Ticket> findAllTicketsPaginated(Pageable pageable);
    List<Ticket> findAllTicketsList();
}
