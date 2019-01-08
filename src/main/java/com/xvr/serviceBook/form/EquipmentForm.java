package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;

public class EquipmentForm {

    private Long id;
    private String name;
    private String description;
    private Department department;

    public EquipmentForm() {
    }

    public EquipmentForm(Long id, String name, String description, Department department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
