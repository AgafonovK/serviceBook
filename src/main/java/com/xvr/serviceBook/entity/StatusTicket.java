package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "status_ticket")
public class StatusTicket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name")
    private String statusName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusTicket")
    private Set<Ticket> tickets;

    public StatusTicket(Long id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "StatusTicket{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
