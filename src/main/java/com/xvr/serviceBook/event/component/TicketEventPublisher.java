package com.xvr.serviceBook.event.component;

import com.xvr.serviceBook.entity.Ticket;
import com.xvr.serviceBook.event.TicketCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TicketEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TicketEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    void publishEvent(TicketCreateEvent ticketCreateEvent){
        applicationEventPublisher.publishEvent(ticketCreateEvent);
    }
}

