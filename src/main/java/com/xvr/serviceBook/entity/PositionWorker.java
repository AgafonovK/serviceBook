package com.xvr.serviceBook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positionWorker")
public class PositionWorker {


    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position_name")
    private String positionName;

    public PositionWorker() {
    }

    public PositionWorker(Long id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}


