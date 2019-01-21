package com.xvr.serviceBook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_ticket")
public class StatusTicket {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status_name")
    private String statusName;

    public StatusTicket() {
    }

    public StatusTicket(Long id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
