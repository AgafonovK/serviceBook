package com.xvr.serviceBook.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "position_name", length = 32, nullable = false)
    private String positionName;

    @Column(name = "first_name",length = 32, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;

    @Column(name = "patronymic", length = 32, nullable = false)
    private String patronymic;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "date_accept")
    private LocalDate dateAccept;

    @Column(name = "date_fired")
    private LocalDate dateFired;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

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

    public LocalDate getDateAccept()
    {

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
