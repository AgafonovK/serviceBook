package com.xvr.serviceBook.service.servicedto;

import com.xvr.serviceBook.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
public class TicketServiceDto {


    private Long reportId;

    private StatusTicket status;

    private PriorityTicket priorityTicket;

    private Set<Worker> worker;

    private String ticketDescription;

    private Department clientDepartment;

    private Equipment equipment;

    private LocalDateTime startDateTicket;

    private LocalDateTime endDateTicket;
}
