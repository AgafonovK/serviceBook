package com.xvr.serviceBook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_type")
public class TypeEquipment {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="type")
    private String type;

    public TypeEquipment() {
    }

    public TypeEquipment(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
