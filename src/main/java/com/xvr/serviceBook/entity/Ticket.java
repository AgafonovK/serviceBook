package com.xvr.serviceBook.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_ticket_id")
    private StatusTicket status;

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "priority_id" )
    private Priority priority;

    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker client;

    @Column(name = "description")
    private String ticketDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department clientDepartment;
    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;

    @Column(name = "start_date")
    private LocalDate startDateTicket;

    @Column(name = "end_date")
    private LocalDate endDateTicket;

    public Ticket() {
    }

    public Department getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(Department clientDepartment) {
        this.clientDepartment = clientDepartment;
    }

    public Ticket(Long id, StatusTicket status, Priority priority,
                  Worker client,
                  String ticketDescription,
                  Department clientDepartment, Equipment equipment, LocalDate startDateTicket, LocalDate endDateTicket) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.client = client;
        this.ticketDescription = ticketDescription;
        this.clientDepartment = clientDepartment;
        this.equipment = equipment;
        this.startDateTicket = startDateTicket;
        this.endDateTicket = endDateTicket;
    }

}
