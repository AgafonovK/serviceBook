package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class TicketForm {


    private Long id;

    @NotEmpty
    @Size(min = 4, max = 500, message = "Не правильный размер сообщения")
    private String ticketDescription;

    @PastOrPresent
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateTicket;

    //@FutureOrPresent
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateTicket;

    @NotNull
    private StatusTicket statusTicket;

    private PriorityTicket priorityTicket;

    @NotNull
    private Set<Worker> workers;


    private Department clientDepartment;

    private Equipment equipment;


}
