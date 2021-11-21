package com.xvr.serviceBook.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "worker")
public class Worker extends RepresentationModel<Worker> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_worker_id")
    private PositionWorker positionWorker;

    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;

    @Column(name = "patronymic", length = 32, nullable = false)
    private String patronymic;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "date_accept")
    private LocalDate dateAccept;

    @Column(name = "date_fired")
    private LocalDate dateFired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Worker(Long id, PositionWorker positionWorker, String firstName, String lastName, String patronymic, Long phone, String email, LocalDate dateAccept, LocalDate dateFired, Department department) {
        this.id = id;
        this.positionWorker = positionWorker;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.dateAccept = dateAccept;
        this.dateFired = dateFired;
        this.department = department;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "worker_ticket",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id")
    )
    private Set<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.getWorkers().add(this);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.getWorkers().remove(this);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", positionWorker=" + positionWorker.getPositionName() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", dateAccept=" + dateAccept +
                ", dateFired=" + dateFired +
                ", department=" + department.getName() +
                '}';
    }
}
