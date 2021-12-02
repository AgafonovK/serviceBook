package com.xvr.serviceBook.event.listener;

import com.xvr.serviceBook.event.AppUserCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@EnableAsync
public class AppUserCreateListener {

    private static final Logger logger = LoggerFactory.getLogger(AppUserCreateListener.class);

    @Async
    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void addWorkerByAppUserCreate(AppUserCreateEvent appUserCreateEvent) {
        logger.info("Input EVENT: create new AppUser! ");
    }
}
