package com.xvr.serviceBook.form;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class DepartmentForm {

    private Long id;
    private String name;

    public DepartmentForm() {
    }

    public DepartmentForm(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
