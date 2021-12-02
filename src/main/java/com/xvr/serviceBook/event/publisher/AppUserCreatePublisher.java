package com.xvr.serviceBook.event.publisher;

import com.xvr.serviceBook.event.AppUserCreateEvent;
import com.xvr.serviceBook.event.TicketCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppUserCreatePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AppUserCreatePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    void publishEvent(AppUserCreateEvent appUserCreateEvent){
        applicationEventPublisher.publishEvent(appUserCreateEvent);
    }
}
