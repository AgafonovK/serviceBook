package com.xvr.serviceBook.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class TicketsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long numberEvent;
    private Long ticketId;
    private Long whatHappen;
    private ZonedDateTime changeTime;
    private Long whoChange;
    private Long result;
    private String comment;


}
