package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.repository.TicketHistoryRepository;
import com.xvr.serviceBook.service.TicketHistoryService;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketHistoryServiceImpl implements TicketHistoryService {

    private final TicketHistoryRepository ticketHistoryRepository;

    @Autowired
    public TicketHistoryServiceImpl(TicketHistoryRepository ticketHistoryRepository) {
        this.ticketHistoryRepository = ticketHistoryRepository;
    }

    @Override
    public void save(TicketServiceDto ticketServiceDto) {
        /** TicketHistory:
         *   private Long numberEvent;
         *     private Long id;
         *     private Long whatHappen;
         *     private Date changeTime;
         *     private Long whoChange;
         *     private Long result;
         *     private String comment;

        TicketsHistory ticketsHistory = TicketsHistory.builder()
                .numberEvent(2L)
                .id(ticketServiceDto.getReportId())
                .whatHappen(3L)
                .changeTime(Date.from(Instant.now()))
                .whoChange(2L)
                .result(1L)
                .comment("op").build();*/
    }
}
