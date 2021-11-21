package com.xvr.serviceBook.event.component;

import com.xvr.serviceBook.entity.TicketsHistory;
import com.xvr.serviceBook.entity.Worker;
import com.xvr.serviceBook.event.TicketCreateEvent;
import com.xvr.serviceBook.service.TicketHistoryService;
import com.xvr.serviceBook.service.TicketService;
import com.xvr.serviceBook.service.email.DefaultEmailService;
import com.xvr.serviceBook.service.servicedto.TicketServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@EnableAsync
@Component
public class TicketCreateEventListener {

    private final DefaultEmailService defaultEmailService;
    private final TicketHistoryService ticketHistoryService;
    private final UserDetailsService userDetailsService;

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
        System.out.println("TESTSTSTSTS ");//  userDetailsService.loadUserByUsername(""));
        workersMailList.forEach(sendToWorker -> {
            defaultEmailService.sendSimpleMessage(sendToWorker, "from: noreply@fo.ru",
                    "Создана заявка с темой: " + ticketMessage);
        });
    }

    @Async
    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Order(2)
    public void addTicketHistoryByTicketCreateEventListener(TicketCreateEvent ticketCreateEvent){
        TicketServiceDto ticketServiceDto = TicketServiceDto.builder()
                .ticketDescription(ticketCreateEvent.getTicket().getTicketDescription())
                .startDateTicket(ticketCreateEvent.getTicket().getStartDateTicket()).build();
        System.out.println("SECOOOND LISTENEEEEEEERRR");
        //ticketHistoryService.save(ticketCreateEvent.getTicket());
    }
}
