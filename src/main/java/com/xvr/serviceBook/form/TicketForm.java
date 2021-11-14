package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
// @Builder not worked
@RequiredArgsConstructor
@ToString
public class TicketForm {


    private Long reportId;

    @NotEmpty
    private StatusTicket status;

    @NotEmpty
    private Priority priority;

    private Worker client;

    @NotEmpty
    private String ticketDescription;

    private Department clientDepartment;

    @NotEmpty
    private Equipment equipment;

    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateTicket;

    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateTicket;
}
