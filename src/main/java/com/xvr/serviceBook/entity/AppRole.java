package com.xvr.serviceBook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long appRoleId;


    @Column(name = "role_name", nullable = false, length = 30)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "appRole")
    private Set<AppUser> appUsers = new HashSet<>();

}
