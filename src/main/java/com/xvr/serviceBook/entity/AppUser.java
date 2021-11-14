package com.xvr.serviceBook.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 32, nullable = false)
    private String userName;

    @Column(name = "encryptedPassword", nullable = false)
    private String encryptedPassword;

    @Column(name = "enable_user", length = 1, nullable = false)
    private int enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Worker worker;

}
