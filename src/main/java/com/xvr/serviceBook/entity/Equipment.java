package com.xvr.serviceBook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location",nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_type_id")
    private TypeEquipment typeEquipment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
    private Set<Ticket> ticket;

    public Equipment(Long id, String name, String description, String location, Department department, TypeEquipment typeEquipment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.department = department;
        this.typeEquipment = typeEquipment;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", department=" + department +
                ", typeEquipment=" + typeEquipment +
                '}';
    }
}
