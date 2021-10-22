package com.xvr.serviceBook.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Column(name = "position_name", length = 32, nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_worker_id")
    private PositionWorker positionWorker;

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
    @JoinColumn(name = "department_id")
    private Department department;



    public Worker() {
    }

    public Worker(Long id, PositionWorker positionWorker, String firstName, String lastName, String patronymic, Long phone, String email, LocalDate dateAccept, LocalDate dateFired, Department department) {
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

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", positionWorker=" + positionWorker +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", dateAccept=" + dateAccept +
                ", dateFired=" + dateFired +
                ", department=" + department +
                '}';
    }
}
