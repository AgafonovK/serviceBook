package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "equipment_type")
public class TypeEquipment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type")
    private String type;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "typeEquipment")
    private Equipment equipment;


}
