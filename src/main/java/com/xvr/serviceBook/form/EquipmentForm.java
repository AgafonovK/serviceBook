package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.TypeEquipment;

public class EquipmentForm {

    private Long id;
    private String name;
    private String description;
    private Department department;
    private TypeEquipment typeEquipment;
    private String location;
    public EquipmentForm() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EquipmentForm(Long id, String name, String description, Department department, TypeEquipment typeEquipment, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
        this.typeEquipment = typeEquipment;
        this.location = location;
    }

    public TypeEquipment getTypeEquipment() {
        return typeEquipment;
    }

    public void setTypeEquipment(TypeEquipment typeEquipment) {
        this.typeEquipment = typeEquipment;
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
