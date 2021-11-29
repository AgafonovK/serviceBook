package com.xvr.serviceBook.service.servicedto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class TicketHistoryServiceDto {

    private Long id;
    private Long numberEvent;
    private Long ticketId;
    private Long whatHappen;
    private ZonedDateTime changeTime;
    private Long whoChange;
    private Long result;
    private String comment;
}
