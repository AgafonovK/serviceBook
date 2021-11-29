package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastUsed;

}
