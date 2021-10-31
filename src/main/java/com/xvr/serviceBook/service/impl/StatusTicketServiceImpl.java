package com.xvr.serviceBook.service.impl;

import com.xvr.serviceBook.service.StatusTicketService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusTicketServiceImpl implements StatusTicketService {

    private final StatusTicketService statusTicketService;

    @Autowired
    public StatusTicketServiceImpl(StatusTicketService statusTicketService){
        this.statusTicketService = statusTicketService;
    }




}
