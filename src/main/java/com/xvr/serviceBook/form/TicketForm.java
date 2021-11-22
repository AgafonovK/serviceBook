package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class TicketForm {


    private Long ticketId;

    @NotNull
    private StatusTicket status;

    private PriorityTicket priorityTicket;

    private Set<Worker> workers;

    @NotEmpty
    private String ticketDescription;

    private Department clientDepartment;

    //@NotNull
    private Equipment equipment;

    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateTicket;

    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateTicket;
}
