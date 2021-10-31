package com.xvr.serviceBook.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id",nullable = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Worker getClient() {
        return client;
    }

    public void setClient(Worker client) {
        this.client = client;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public LocalDate getStartDateTicket() {
        return startDateTicket;
    }

    public void setStartDateTicket(LocalDate startDateTicket) {
        this.startDateTicket = startDateTicket;
    }

    public LocalDate getEndDateTicket() {
        return endDateTicket;
    }

    public void setEndDateTicket(LocalDate endDateTicket) {
        this.endDateTicket = endDateTicket;
    }
}
