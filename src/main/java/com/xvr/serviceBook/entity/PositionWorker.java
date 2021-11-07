package com.xvr.serviceBook.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "position_worker")
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

}


