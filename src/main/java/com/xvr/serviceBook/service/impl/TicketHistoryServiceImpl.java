package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.entity.TicketsHistory;
import com.xvr.serviceBook.repository.TicketHistoryRepository;
import com.xvr.serviceBook.service.TicketHistoryService;
import com.xvr.serviceBook.service.servicedto.TicketHistoryServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;

@Service
public class TicketHistoryServiceImpl implements TicketHistoryService {

    private final TicketHistoryRepository ticketHistoryRepository;

    @Autowired
    public TicketHistoryServiceImpl(TicketHistoryRepository ticketHistoryRepository) {
        this.ticketHistoryRepository = ticketHistoryRepository;
    }

    @Override
    public void save(TicketHistoryServiceDto ticketHistoryServiceDto) {
        TicketsHistory ticketsHistory = TicketsHistory.builder()
                .numberEvent(2L)
                .id(ticketHistoryServiceDto.getTicketId())
                .whatHappen(3L)
                .changeTime(ZonedDateTime.from(Instant.now()))
                .whoChange(2L)
                .result(1L)
                .comment("op").build();
        ticketHistoryRepository.save(ticketsHistory);
    }
}
