package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class TicketForm {


    private Long reportId;

    private StatusTicket status;

    private Priority priority;

    private Worker client;

    @NotEmpty
    private String ticketDescription;

    private Department clientDepartment;

    private Equipment equipment;

    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateTicket;

    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDateTicket;

    public TicketForm() {
    }

    public TicketForm(Long reportId, StatusTicket status, Priority priority, Worker client, String ticketDescription, Department clientDepartment, Equipment equipment, LocalDate startDateTicket, LocalDate endDateTicket) {
        this.reportId = reportId;
        this.status = status;
        this.priority = priority;
        this.client = client;
        this.ticketDescription = ticketDescription;
        this.clientDepartment = clientDepartment;
        this.equipment = equipment;
        this.startDateTicket = startDateTicket;
        this.endDateTicket = endDateTicket;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public Department getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(Department clientDepartment) {
        this.clientDepartment = clientDepartment;
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

    @Override
    public String toString() {
        return "TicketForm{" +
                "reportId=" + reportId +
                ", status=" + status +
                ", priority=" + priority +
                ", client=" + client +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", clientDepartment=" + clientDepartment +
                ", equipment=" + equipment +
                ", startDateTicket=" + startDateTicket +
                ", endDateTicket=" + endDateTicket +
                '}';
    }
}
