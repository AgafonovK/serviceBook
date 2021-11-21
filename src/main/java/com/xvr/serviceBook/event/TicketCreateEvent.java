package com.xvr.serviceBook.event;

import com.xvr.serviceBook.entity.Ticket;
import lombok.Getter;

@Getter
public class TicketCreateEvent {

    public Ticket ticket;

    public TicketCreateEvent(Object object, Ticket ticket){
        this.ticket = ticket;
    }


}
