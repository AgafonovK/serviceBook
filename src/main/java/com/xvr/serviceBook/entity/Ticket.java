package com.xvr.serviceBook.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column (name = "priority", nullable = false)
    private String priority;

    //TODO

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker client;

    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    @Column(name = "description")
    private String ticketDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department clientDepartment;
    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    public Ticket() {
    }

    public Department getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(Department clientDepartment) {
        this.clientDepartment = clientDepartment;
    }

    public Ticket(Long id, String status, String priority,
                  Worker client,
                  Worker worker, String ticketDescription,
                  Department clientDepartment, Equipment equipment, LocalDate start_date, LocalDate end_date) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.client = client;
        this.worker = worker;
        this.ticketDescription = ticketDescription;
        this.clientDepartment = clientDepartment;
        this.equipment = equipment;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Worker getClient() {
        return client;
    }

    public void setClient(Worker client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
