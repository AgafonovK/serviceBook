package com.xvr.serviceBook.event.listener;

import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.event.TicketCreateEvent;
import com.xvr.serviceBook.service.TicketHistoryService;
import com.xvr.serviceBook.service.email.DefaultEmailService;
import com.xvr.serviceBook.service.servicedto.TicketHistoryServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EnableAsync
@Component
public class TicketCreateEventListener {

    private final DefaultEmailService defaultEmailService;
    private final TicketHistoryService ticketHistoryService;
    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(TicketCreateEventListener.class);


    @Autowired
    public TicketCreateEventListener(DefaultEmailService defaultEmailService, TicketHistoryService ticketHistoryService,
                                     UserDetailsService userDetailsService) {
        this.defaultEmailService = defaultEmailService;
        this.ticketHistoryService = ticketHistoryService;
        this.userDetailsService = userDetailsService;
    }

    @Async
    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Order(1)
    public void sendNotificationEmailByTicketCreateEventListener(TicketCreateEvent ticketCreateEvent){
        List<String> workersMailList = ticketCreateEvent.getTicket().getWorkers().stream()
                .map(Worker::getEmail)
                .collect(Collectors.toList());
        String ticketMessage = ticketCreateEvent.getTicket().getTicketDescription();
        //TODO load user by Principal
        logger.info("Create event send Notification email to worker's.");
        //  userDetailsService.loadUserByUsername(""));
        workersMailList.forEach(sendToWorker -> {
            defaultEmailService.sendSimpleMessage(sendToWorker, "from: noreply@foo.ru",
                    "Создана заявка с темой: " + ticketMessage);
        });
    }

    @Async
    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Order(2)
    public void addTicketHistoryByTicketCreateEventListener(TicketCreateEvent ticketCreateEvent){
        //TODO fill dto to the end
        TicketHistoryServiceDto ticketHistoryServiceDto = TicketHistoryServiceDto.builder()
                .ticketId(ticketCreateEvent.getTicket().getId())
                .changeTime(ZonedDateTime.now())
                .build();
        logger.info("Success add ticket history to DB");
        try {
            ticketHistoryService.save(ticketHistoryServiceDto);
        }catch (Exception e){
            logger.error("Can not create ticket History entity in DB. ");
        }
    }

}
