package com.xvr.serviceBook.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "app_role")
public class AppRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long appRoleId;


    @Column(name = "role_name", nullable = false, length = 30)
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "appRole")
    private Set<AppUser> appUsers;

    @Override
    public String toString() {
        return "AppRole{" +
                "appRoleId=" + appRoleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
