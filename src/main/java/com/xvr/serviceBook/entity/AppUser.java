package com.xvr.serviceBook.entity;

import javax.persistence.*;


@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", length = 32, nullable = false)
    private String userName;

    @Column(name = "encryptedPassword", nullable = false)
    private String encryptedPassword;

    @Column(name = "enable_user", length = 1, nullable = false)
    private int enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public int isEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
