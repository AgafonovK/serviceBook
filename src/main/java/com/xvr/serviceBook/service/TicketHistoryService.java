package com.xvr.serviceBook.service;

import com.xvr.serviceBook.service.servicedto.TicketHistoryServiceDto;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;

public interface TicketHistoryService {

    void save(TicketServiceDto ticketServiceDto);

}
