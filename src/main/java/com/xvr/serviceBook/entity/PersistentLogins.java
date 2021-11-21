package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {


    @Column(name = "USERNAME")
    private String userName;

    @Id
    @Column(name = "series")
    private String series;

    @Column(name = "token")
    private String token;

    @Column(name = "last_used")
    private Date lastUsed;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
