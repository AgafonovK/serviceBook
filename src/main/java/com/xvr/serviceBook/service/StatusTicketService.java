package com.xvr.serviceBook.service;

import com.xvr.serviceBook.entity.StatusTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatusTicketService {

    Page<StatusTicket> findAllStatusTicketPageable(Pageable pageable);
    List<StatusTicket> findAllStatusTicketList();

}
