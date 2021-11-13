package com.xvr.serviceBook.entity;

import javax.persistence.*;

@Entity
@Table(name = "priority")
public class Priority {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority_name", nullable = false)
    private String priorityName;

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public Priority() {
    }

    public Priority(Long id, String priorityName) {
        this.id = id;
        this.priorityName = priorityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
