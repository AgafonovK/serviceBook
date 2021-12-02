package com.xvr.serviceBook.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String ticketDescription;

    @Column(name = "start_date", updatable = false)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateTicket;

    @Column(name = "end_date")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_ticket_id",nullable = false)
    private StatusTicket statusTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private PriorityTicket priorityTicket;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tickets")
    @Singular
    private Set<Worker> workers;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department clientDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;



    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", statusTicket=" + statusTicket.getStatusName() +
                ", priority=" + priorityTicket.getPriorityName() +
                ", client=" + workers.toString() +
                ", ticketDescription='" + ticketDescription + '\'' +
                ", clientDepartment=" + clientDepartment.getName() +
                ", equipment=" + equipment.getName() +
                ", startDateTicket=" + startDateTicket +
                ", endDateTicket=" + endDateTicket +
                '}';
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
