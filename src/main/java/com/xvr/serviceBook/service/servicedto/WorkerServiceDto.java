package com.xvr.serviceBook.service.servicedto;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.PositionWorker;
import com.xvr.serviceBook.entity.Ticket;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class WorkerServiceDto {

    private Long id;

    private PositionWorker positionWorker;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Long phone;

    private String email;

    private LocalDate dateAccept;

    private LocalDate dateFired;

    private Department department;

    private Set<Ticket> tickets;
}
