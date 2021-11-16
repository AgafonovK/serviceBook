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
@Table(name = "priority")
public class PriorityTicket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority_name", nullable = false)
    private String priorityName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "priorityTicket")
    private Set<Ticket> ticket;


    public PriorityTicket(Long id, String priorityName) {
        this.id = id;
        this.priorityName = priorityName;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", priorityName='" + priorityName + '\'' +
                '}';
    }
}
