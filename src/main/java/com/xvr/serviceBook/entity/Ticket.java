package com.xvr.serviceBook.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_ticket_id")
    @ToString.Exclude
    private StatusTicket status;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    //@JoinColumn(name = "priority_id" )
    private Priority priority;

    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    @ToString.Exclude
    private Worker client;

    @Column(name = "description")
    private String ticketDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Department clientDepartment;
    //TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Equipment equipment;

    @Column(name = "start_date")
    private LocalDate startDateTicket;

    @Column(name = "end_date")
    private LocalDate endDateTicket;

    public Department getClientDepartment() {
        return clientDepartment;
    }

    public void setClientDepartment(Department clientDepartment) {
        this.clientDepartment = clientDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return id != null && Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
