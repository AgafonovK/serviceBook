package com.xvr.serviceBook.form;

import com.xvr.serviceBook.entity.Department;
import com.xvr.serviceBook.entity.PositionWorker;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class WorkerForm {

    private Long id;

    private PositionWorker positionWorker;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String patronymic;

    private Long phone;

    @Email
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateAccept;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFired;

    private Department department;

    public WorkerForm() {
    }

    public WorkerForm(Long id, PositionWorker positionWorker, String firstName, String lastName, String patronymic, Long phone, String email, LocalDate dateAccept, LocalDate dateFired, Department department) {
        this.id = id;
        this.positionWorker = positionWorker;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.dateAccept = dateAccept;
        this.dateFired = dateFired;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionWorker getPositionWorker() {
        return positionWorker;
    }

    public void setPositionWorker(PositionWorker positionWorker) {
        this.positionWorker = positionWorker;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAccept() {
        return dateAccept;
    }

    public void setDateAccept(LocalDate dateAccept) {
        this.dateAccept = dateAccept;
    }

    public LocalDate getDateFired() {
        return dateFired;
    }

    public void setDateFired(LocalDate dateFired) {
        this.dateFired = dateFired;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
